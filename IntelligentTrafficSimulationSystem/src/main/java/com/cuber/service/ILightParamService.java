package com.cuber.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cuber.entity.LightParam;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cuber.entity.vo.LightParamVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cuber
 * @since 2022-04-26
 */
public interface ILightParamService extends IService<LightParam> {

    IPage<LightParam> getPageList(LightParamVO lightParamVO);

    List<LightParam> getByUserId(int userid);
}
