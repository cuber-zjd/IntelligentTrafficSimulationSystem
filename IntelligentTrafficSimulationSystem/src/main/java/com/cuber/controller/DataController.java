package com.cuber.controller;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuber.controller.utils.R;
import com.cuber.entity.Data;
import com.cuber.entity.Dataandspeed;
import com.cuber.entity.DetectSetting;
import com.cuber.entity.vo.*;
import com.cuber.service.IDataService;
import com.cuber.service.IDataandspeedService;
import com.cuber.service.IDetectSettingService;
import com.cuber.utils.Command;

import com.cuber.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * <p>
 * 前端控制器
 * </p>
 * @author cuber
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/datas")
public class DataController {

    @Autowired
    private IDataService dataService;

    @Autowired
    private IDataandspeedService dataandspeedService;

    @Autowired
    private IDetectSettingService detectSettingService;

    /**
     * 接收上传的文件
     * @param uploadFile
     * @param fileName
     * @return
     */
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile uploadFile, @RequestParam("filename") String fileName) {

        File file1 = new File("D:\\workspace\\IntelligentTrafficSimulationSystem\\video\\" + fileName + ".mp4");
        if (!file1.exists()) {
            file1.mkdirs();
        }
        try {
            uploadFile.transferTo(file1);
            return new R(true, null, "上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new R(true, null, "上传失败");
    }

    /**
     * 通过算法添加数据
     *
     * @param autoAddVO
     * @return
     */
    @PostMapping("/autoadd")
    public Result autoAdd(@RequestBody AutoAddVO autoAddVO) {

        DetectSetting byId = detectSettingService.getById(autoAddVO.getParamId());

        String commandStr="D:\\install\\python\\Anaconda3\\envs\\pytorch\\Scripts\\activate.bat && " +
                "cd D:\\workspace\\IntelligentTrafficSimulationSystem\\algorithm\\yolov5-deepsort && " +
                "python D:\\workspace\\IntelligentTrafficSimulationSystem\\algorithm\\yolov5-deepsort\\count_car.py " +
                autoAddVO.getDetectDirection() +
                //检测视频的路径
                " D:\\workspace\\IntelligentTrafficSimulationSystem\\video\\" +
                autoAddVO.getUserid() + "--"
                + autoAddVO.getRoadid() + "--"
                + DateUtil.format(autoAddVO.getDate(), "yyyy-MM-dd-HH-mm-ss") + "--"
                + autoAddVO.getDirection() + "--" +
                autoAddVO.getDetectDirection() + ".mp4" +
                //检测参数
                " " + byId.getLinePosx().toString() +
                " " + byId.getLinePosy().toString() +
                " " + byId.getLineLength().toString() +
                " " + byId.getTwoLineDistant().toString()+
                " " + byId.getDistant().toString();
        //通过调用python算法获取视频中的车辆信息
        List<String> message = Command.exeCmd(commandStr);
        //获取速度列表和视频时间
        List<CarVO> carVOs = new ArrayList<>();
        int duration = 0;
        for (String s : message) {
            String[] sstr = s.split(" ");
            if ("car".equals(sstr[0])) {
                if (35 > Math.abs(Double.parseDouble(sstr[2])) && 3 < Math.abs(Double.parseDouble(sstr[2]))) {
                    CarVO carVO = new CarVO();
                    carVO.setSpeed(Math.abs(Double.parseDouble(sstr[2])));
                    carVO.setTime(Double.parseDouble(sstr[4]));
                    carVOs.add(carVO);
                }
            }
            if ("duration".equals(sstr[0])) {
                Double doubleDuration = Double.parseDouble(sstr[1]);
                duration = doubleDuration.intValue();
            }
        }

        //时间格式转换
        Date date2 = new Date();
        date2.setTime(autoAddVO.getDate().getTime() + duration * 1000);
        Timestamp starttime = new Timestamp(autoAddVO.getDate().getTime());
        Timestamp endtime = new Timestamp(date2.getTime());

        //创建data对象并保存
        Data data = new Data();
        data.setStarttime(starttime);
        data.setEndtime(endtime);
        data.setRoadid(autoAddVO.getRoadid());
        data.setDirection(autoAddVO.getDirection());
        data.setUserid(autoAddVO.getUserid());
        data.setParamId(autoAddVO.getParamId());
        data.setSource(1);
        dataService.save(data);

        //存储速度列表
        List<Dataandspeed> dataandspeeds = new ArrayList<>();
        for (CarVO carVO : carVOs) {
            Dataandspeed dataandspeed = new Dataandspeed(data.getId()
                    , carVO.getSpeed() * 3.6
                    , new Timestamp(autoAddVO.getDate().getTime() + Math.round(carVO.getTime()) * 1000));
            dataandspeeds.add(dataandspeed);
        }
        dataandspeedService.saveBatch(dataandspeeds);

        return Result.success();
    }


    /**
     * 手动添加数据
     *
     * @param manualAddVO
     * @return
     */
    @PostMapping("manualAdd")
    public Result manualAdd(@RequestBody ManualAddVO manualAddVO) {
        //创建data对象并保存
        Data data = new Data();
        data.setUserid(manualAddVO.getUserid());
        data.setRoadid(manualAddVO.getRoadid());
        data.setDirection(manualAddVO.getDirection());
        data.setStarttime(new Timestamp(manualAddVO.getStartDate().getTime()));
        data.setEndtime(new Timestamp(manualAddVO.getEndDate().getTime()));

        int duration = (int) ((manualAddVO.getEndDate().getTime() - manualAddVO.getStartDate().getTime()) / 1000);

        data.setParamId(0);
        data.setSource(2);
        dataService.save(data);

        //随机生成速度列表
        List<Dataandspeed> dataandspeeds = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < manualAddVO.getNum(); i++) {
            Dataandspeed dataandspeed = new Dataandspeed(data.getId()
                    , random.nextDouble() * (manualAddVO.getMaxSpeed() - manualAddVO.getMinSpeed()) + manualAddVO.getMinSpeed()
                    , new Timestamp(manualAddVO.getStartDate().getTime() + random.nextInt(duration) * 1000));
            dataandspeeds.add(dataandspeed);
        }

        dataandspeedService.saveBatch(dataandspeeds);
        return Result.success();
    }

    /**
     * 根据路口id查询对应路段相关数据
     * @param id
     * @return
     */
    @GetMapping("/getByRoadId/{id}")
    public Result getByRoadId(@PathVariable Integer id) {
        return Result.success(dataService.getByRoadId(id));
    }

    /**
     * 根据用户的id查询该用户的存储的道路数据
     *
     * @param userid
     * @param current
     * @return Result
     */
    @PostMapping("/getByUser/{userid}/{current}")
    public Result getByUserId(@PathVariable Integer userid, @PathVariable Integer current, @RequestBody DataQueryVO dataQueryVO) {
        IPage<DataVO> byUserId = dataService.getByUserId(userid, current, dataQueryVO);
        return Result.success((byUserId));
    }


    /**
     * 根据数据的id查询对应数据
     * @param id
     * @return Result
     */
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(dataService.getById(id));
    }

    /**
     * 根据id更改相应的数据
     * @param data
     * @return Result
     */
    @PutMapping()
    public Result updateById(@RequestBody Data data) {
        dataService.updateById(data);
        return Result.success();
    }

    /**
     * 根据id删除对应的数据
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable Integer id) {
        dataService.removeById(id);
        dataandspeedService.removeByDataId(id);
        return Result.success();
    }


}

