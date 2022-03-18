package com.cuber.service;

import com.cuber.entity.Intersection;
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
public interface IIntersectionService extends IService<Intersection> {

    public List<Intersection> getAllByUserId(Integer userid);

    public List<Intersection> getByName(String name);
}
