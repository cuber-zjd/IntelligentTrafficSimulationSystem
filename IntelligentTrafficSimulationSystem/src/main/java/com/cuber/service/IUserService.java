package com.cuber.service;

import com.cuber.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cuber
 * @since 2022-02-07
 */
public interface IUserService extends IService<User> {
    User getByUserName(User user);
}
