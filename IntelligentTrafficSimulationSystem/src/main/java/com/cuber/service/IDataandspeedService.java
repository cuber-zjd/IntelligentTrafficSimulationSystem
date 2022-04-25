package com.cuber.service;

import com.cuber.entity.Dataandspeed;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuber.entity.vo.FirstChartVO;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cuber
 * @since 2022-04-04
 */
public interface IDataandspeedService extends IService<Dataandspeed> {

    List<Dataandspeed> getByDataId(Integer dataId);


    boolean removeByDataId(Integer id);

    List<Double> getByDataIdsAndTime(List<Integer> dataIds, Date startTime, Date endTime);

    List<Dataandspeed> getAsSegment(List<Integer> dataIds, FirstChartVO chartVO);
}
