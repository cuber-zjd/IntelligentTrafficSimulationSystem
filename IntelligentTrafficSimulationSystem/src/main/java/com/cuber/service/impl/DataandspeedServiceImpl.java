package com.cuber.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cuber.entity.Dataandspeed;
import com.cuber.entity.vo.FirstChartVO;
import com.cuber.mapper.DataandspeedMapper;
import com.cuber.service.IDataandspeedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cuber
 * @since 2022-04-04
 */
@Service
public class DataandspeedServiceImpl extends ServiceImpl<DataandspeedMapper, Dataandspeed> implements IDataandspeedService {

    @Autowired
    private DataandspeedMapper dataandspeedMapper;

    @Override
    public List<Dataandspeed> getByDataId(Integer dataId) {

        List<Dataandspeed> list = list(Wrappers.lambdaQuery(Dataandspeed.class)
                .eq(Dataandspeed::getDataid, dataId)
        );
        return list;
    }

    @Override
    public boolean removeByDataId(Integer id) {
        return remove(Wrappers.lambdaUpdate(Dataandspeed.class).eq(Dataandspeed::getDataid, id));
    }

    @Override
    public List<Double> getByDataIdsAndTime(List<Integer> dataIds, Date startTime, Date endTime) {

        List<Dataandspeed> list = list(Wrappers
                .lambdaQuery(Dataandspeed.class)
                .in(Dataandspeed::getDataid, dataIds)
                .ge(Dataandspeed::getPassTime, new Timestamp(startTime.getTime()))
                .le(Dataandspeed::getPassTime, new Timestamp(endTime.getTime())));
        List<Double> speeds = list.stream().map(Dataandspeed::getSpeed).collect(Collectors.toList());
        return speeds;
    }

    @Override
    public List<Dataandspeed> getAsSegment(List<Integer> dataIds, FirstChartVO chartVO) {
        List<Dataandspeed> list = list(Wrappers
                .lambdaQuery(Dataandspeed.class)
                .in(Dataandspeed::getDataid, dataIds)
                .ge(Dataandspeed::getPassTime, new Timestamp(chartVO.getStartTime().getTime()))
                .le(Dataandspeed::getPassTime, new Timestamp(chartVO.getEndTime().getTime())));
       return list;
    }
}
