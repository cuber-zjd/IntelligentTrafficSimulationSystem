package com.cuber.utils.convert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuber.entity.vo.DataVO;
import com.cuber.mapper.RoadMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author cuber
 * @create 2022/3/30 16:10
 */
public class DataConvert {

    @Autowired
    private RoadMapper roadMapper;

    public static IPage<DataVO> addRoadName(IPage<DataVO> dataVOIPage){
        Set<Integer> roadIds=dataVOIPage.getRecords().stream().map(DataVO::getRoadid).collect(Collectors.toSet());
        System.out.println(roadIds);
        return dataVOIPage;
    }
}
