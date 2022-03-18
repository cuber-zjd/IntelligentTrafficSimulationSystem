package com.cuber.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuber.entity.Intersection;
import com.cuber.mapper.IntersectionMapper;
import com.cuber.service.IIntersectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cuber
 * @since 2022-02-27
 */
@Service
public class IntersectionServiceImpl extends ServiceImpl<IntersectionMapper, Intersection> implements IIntersectionService {

    @Override
    public List<Intersection> getAllByUserId(Integer userid) {
        QueryWrapper<Intersection> qw=new QueryWrapper<>();
        qw.eq("userid",userid);
        return list(qw);
    }

    @Override
    public List<Intersection> getByName(String name) {
        QueryWrapper<Intersection> qw=new QueryWrapper<>();
        qw.like("name",name);
        return list(qw);
    }
}
