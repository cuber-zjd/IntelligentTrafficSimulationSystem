package com.cuber.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.cuber.controller.utils.R;
import com.cuber.entity.Data;
import com.cuber.entity.Intersection;
import com.cuber.service.IDataService;
import com.cuber.utils.Command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cuber
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/datas")
public class DataController {

    @Autowired
    private IDataService dataService;

    /**
     * 通过算法添加数据
     * @param userid
     * @param sectionid
     * @param date
     * @param direction
     * @param detectDirection
     * @return
     */
    @PostMapping("/autoadd")
    public R autoAdd(@RequestParam int userid,@RequestParam int sectionid, @RequestParam String date,@RequestParam String direction ,@RequestParam String detectDirection){
        System.out.println("test");
        System.out.println(date);
//        通过调用python算法获取视频中的车辆信息
        List<Double> speeds=Command.exeCmd("D:\\install\\python\\Anaconda3\\envs\\pytorch\\Scripts\\activate.bat && " +
                "cd D:\\workspace\\graduationproject\\algorithm\\yolov5-deepsort && " +
                "python D:\\workspace\\graduationproject\\algorithm\\yolov5-deepsort\\count_car.py "+detectDirection+" D:\\workspace\\IntelligentTrafficSimulationSystem\\video\\"+userid+"--"+sectionid+"--"+date+"--"+direction+"--"+detectDirection+".mp4");

        //车辆数量，最大速度，最小速度
        int num=speeds.size();
        double min=speeds.get(0);
        double max=speeds.get(0);
        double sum=0;
        for (Double speed : speeds) {
            sum+=speed;
            if (speed>max){
                max=speed;
            }
            if (speed<min){
                min=speed;
            }
        }
        double avgSpeed=sum/num;


        Date date1 = DateUtil.parse(date,"yyyy-MM-dd-HH-mm-ss");
        Timestamp dateTime=new Timestamp(date1.getTime());

        System.out.println(dateTime);
        Data data=new Data();
        data.setNum(num);
        data.setAvgspeed(avgSpeed);
        data.setStarttime(dateTime);
        data.setMaxspeed(max);
        data.setMinspeed(min);
        data.setSectionid(sectionid);
        data.setDirection(direction);

        return new R(dataService.save(data));
    }

    /**
     * 接收上传的文件
     * @param uploadFile
     * @param fileName
     * @return
     */
    @PostMapping("/upload")
    public R upload(@RequestParam("file")MultipartFile uploadFile,@RequestParam("filename") String fileName){

        File file1 = new File("D:\\workspace\\IntelligentTrafficSimulationSystem\\video\\"+fileName+".mp4");
        if(!file1.exists()){
            file1.mkdirs();
        }
        try {
            uploadFile.transferTo(file1);
            return new R(true,null,"上传成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new R(true,null,"上传失败");
    }

    /**
     * 根据路口查询相关数据
     * @param id
     * @return
     */
    @GetMapping("/getByIntersectionId/{id}")
    public R getByIntersectionId(@PathVariable Integer id){
        return new R(true,dataService.getByIntersectionId(id),"查询成功");
    }




}

