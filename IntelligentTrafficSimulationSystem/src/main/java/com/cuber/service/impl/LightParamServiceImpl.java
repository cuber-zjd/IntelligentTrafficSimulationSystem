package com.cuber.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuber.entity.LightParam;
import com.cuber.entity.vo.LightParamVO;
import com.cuber.mapper.LightParamMapper;
import com.cuber.service.ILightParamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cuber
 * @since 2022-04-26
 */
@Service
public class LightParamServiceImpl extends ServiceImpl<LightParamMapper, LightParam> implements ILightParamService {

    @Autowired
    private LightParamMapper lightParamMapper;

    @Override
    public IPage<LightParam> getPageList(LightParamVO lightParamVO) {
        Page<LightParam> page=new Page<>(lightParamVO.getCurrent(),5);

        return lightParamMapper.selectPage(page,Wrappers.lambdaQuery(LightParam.class)
                .like(StrUtil.isNotEmpty(lightParamVO.getName()),LightParam::getName,lightParamVO.getName())
                .eq(LightParam::getUserid,lightParamVO.getUserid()));
    }

    @Override
    public List<LightParam> getByUserId(int userid) {
        return lightParamMapper.selectList(Wrappers.lambdaQuery(LightParam.class)
                .eq(LightParam::getUserid,userid)
        );
    }
}
