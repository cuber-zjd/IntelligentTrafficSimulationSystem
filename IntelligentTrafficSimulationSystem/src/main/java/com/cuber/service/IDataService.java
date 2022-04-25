package com.cuber.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuber.entity.Data;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuber.entity.vo.DataQueryVO;
import com.cuber.entity.vo.DataVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cuber
 * @since 2022-02-27
 */
public interface IDataService extends IService<Data> {
    public List<Data> getByRoadId(Integer id);

    public IPage<DataVO> getByUserId(Integer userid, Integer current, DataQueryVO dataQueryVO);

    public Data getById(Integer id);
}
