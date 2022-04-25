package com.cuber.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuber.entity.DetectSetting;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuber.entity.vo.DetectParamQueryVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cuber
 * @since 2022-04-06
 */
public interface IDetectSettingService extends IService<DetectSetting> {

    IPage<DetectSetting> getPageList(DetectParamQueryVO queryVO);

    List<DetectSetting> getByUserId(Integer userId);
}
