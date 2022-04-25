package com.cuber.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuber.entity.DetectSetting;
import com.cuber.entity.vo.DetectParamQueryVO;
import com.cuber.mapper.DetectSettingMapper;
import com.cuber.service.IDetectSettingService;
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
 * @since 2022-04-06
 */
@Service
public class DetectSettingServiceImpl extends ServiceImpl<DetectSettingMapper, DetectSetting> implements IDetectSettingService {

    @Autowired
    private DetectSettingMapper detectSettingMapper;

    @Override
    public IPage<DetectSetting> getPageList(DetectParamQueryVO queryVO) {
        Page<DetectSetting> page=new Page<>(queryVO.getCurrent(),5);
        return detectSettingMapper.selectPage(page,
                Wrappers.lambdaQuery(DetectSetting.class)
                        .like(StrUtil.isNotEmpty(queryVO.getName()),DetectSetting::getName,queryVO.getName())
                        .eq(DetectSetting::getUserid,queryVO.getUserid())
                );
    }

    @Override
    public List<DetectSetting> getByUserId(Integer userId) {
        return detectSettingMapper.selectList(Wrappers.lambdaQuery(DetectSetting.class)
                .eq(DetectSetting::getUserid,userId));
    }
}
