package com.cuber.service;

import com.cuber.entity.Data;
import com.baomidou.mybatisplus.extension.service.IService;

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
    public List<Data> getByIntersectionId(Integer id);
}
