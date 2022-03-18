package com.cuber.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuber.entity.Data;
import com.cuber.mapper.DataMapper;
import com.cuber.service.IDataService;
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
public class DataServiceImpl extends ServiceImpl<DataMapper, Data> implements IDataService {

    @Override
    public List<Data> getByIntersectionId(Integer id) {
        QueryWrapper<Data> qw=new QueryWrapper<>();
        qw.eq("sectionid",id);
        List<Data> list = list(qw);
        return list;
    }
}
