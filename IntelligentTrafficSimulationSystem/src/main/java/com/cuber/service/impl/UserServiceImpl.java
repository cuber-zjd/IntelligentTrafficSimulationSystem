package com.cuber.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuber.entity.User;
import com.cuber.mapper.UserMapper;
import com.cuber.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cuber
 * @since 2022-02-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public User getByUserName(User user) {
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("username",user.getUsername());
        User user1=getOne(qw);
        return user1;
    }
}
