package com.cuber.controller;


import com.cuber.entity.Data;
import com.cuber.entity.Dataandspeed;
import com.cuber.entity.Road;
import com.cuber.entity.vo.FirstChartVO;
import com.cuber.service.IDataService;
import com.cuber.service.IDataandspeedService;
import com.cuber.service.IRoadService;
import com.cuber.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cuber
 * @since 2022-04-04
 */
@RestController
@RequestMapping("/dataandspeeds")
public class DataandspeedController {

    @Autowired
    private IDataandspeedService dataandspeedService;

    @Autowired
    private IDataService dataService;

    /**
     * 根据dataid获取对应的具体数据
     * @param dataId
     * @return
     */
    @GetMapping("getByDataId/{dataId}")
    public Result getByDataId(@PathVariable Integer dataId){

        List<Dataandspeed> byDataId = dataandspeedService.getByDataId(dataId);

        List<Double> speeds=new ArrayList<>();
        for (Dataandspeed dataandspeed : byDataId) {
            Double speed = dataandspeed.getSpeed();
            speeds.add(speed);
        }
        return Result.success(speeds);
    }

    @PostMapping("getByRoadIdAndTime")
    public Result getByRoadIdAndTime(@RequestBody FirstChartVO chartVO){
        List<Data> byRoadId = dataService.getByRoadId(chartVO.getRoadId());
        List<Integer> dataIds = byRoadId.stream().map(Data::getId).collect(Collectors.toList());
        return Result.success(dataandspeedService.getByDataIdsAndTime(dataIds,chartVO.getStartTime(),chartVO.getEndTime()));
    }

    @PostMapping("getAllByRoadIdAndTime")
    public Result getAsSegment(@RequestBody FirstChartVO chartVO){
        List<Data> byRoadId = dataService.getByRoadId(chartVO.getRoadId());
        List<Integer> dataIds = byRoadId.stream().map(Data::getId).collect(Collectors.toList());
        return Result.success(dataandspeedService.getAsSegment(dataIds,chartVO));
    }
}

