package com.cuber.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuber.entity.Road;
import com.cuber.entity.vo.RoadQueryVO;
import com.cuber.mapper.RoadMapper;
import com.cuber.service.IRoadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cuber
 * @since 2022-03-27
 */
@Service
public class RoadServiceImpl extends ServiceImpl<RoadMapper, Road> implements IRoadService {

    @Autowired
    private RoadMapper roadMapper;

    @Override
    public IPage<Road> getPageListByUserId(Integer userid, Integer current, RoadQueryVO roadQueryVO) {
        QueryWrapper<Road> qw=new QueryWrapper<>();
        qw.eq("userid",userid);
        qw.like(StrUtil.isNotEmpty(roadQueryVO.getName()),"name",roadQueryVO.getName());

        qw.in(CollUtil.isNotEmpty(roadQueryVO.getRoadTypes()), "type",roadQueryVO.getRoadTypes());

        Page<Road> roadPage=new Page<>(current,5);
        IPage<Road> roadIPage=roadMapper.selectPage(roadPage,qw);
        return roadIPage;
    }

    @Override
    public List<Road> getAllByUserId(Integer userid) {
        List<Road> roadList = list(Wrappers.lambdaQuery(Road.class)
                .eq(Road::getUserid, userid));
        return roadList;
    }
}
