package com.cuber.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuber.entity.Road;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuber.entity.vo.RoadQueryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cuber
 * @since 2022-03-27
 */
public interface IRoadService extends IService<Road> {
    public IPage<Road> getPageListByUserId(Integer userid, Integer current, RoadQueryVO roadQueryVO);

    public List<Road> getAllByUserId(Integer userid);
}
