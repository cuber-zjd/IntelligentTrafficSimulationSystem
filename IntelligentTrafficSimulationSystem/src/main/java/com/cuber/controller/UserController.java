package com.cuber.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cuber.controller.utils.R;
import com.cuber.entity.User;
import com.cuber.mapper.UserMapper;
import com.cuber.service.IUserService;
import com.cuber.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cuber
 * @since 2022-02-07
 */
@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 查询全部
     * @return R
     */
    @GetMapping
    public  R getAll(){
        return new R(true,userService.list(),"查询成功");
    }

    /**
     * 登录
     * @param user
     * @return R
     */
    @PostMapping("/login")
    public R login(@RequestBody User user){
        User byUserName = userService.getByUserName(user);
        if(byUserName!=null&&user.getPassword().equals(byUserName.getPassword())){
            String s = TokenUtils.generateToken(byUserName);
            User user1 = new User();
            user1.setUsername(byUserName.getUsername());
            user1.setId(byUserName.getId());
            return new R(true,user1,s);
        }
        return new R(false,null,"用户名或密码不正确");
    }

    /**
     * 通过id获取全部信息
     * @param id
     * @return R
     */
    @GetMapping("{id}")
    public R getUserById(@PathVariable Integer id){
        return new R(true,userService.getById(id));
    }

}

