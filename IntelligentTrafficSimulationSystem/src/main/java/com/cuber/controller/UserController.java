package com.cuber.controller;


import com.cuber.controller.utils.R;
import com.cuber.entity.User;
import com.cuber.entity.dto.LoginDTO;
import com.cuber.entity.vo.Password;
import com.cuber.service.IUserService;
import com.cuber.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询全部
     * @return Result
     */
    @GetMapping
    public  R getAll(){
        return new R(true,userService.list(),"查询成功");
    }

    /**
     * 登录
     * @param user
     * @return Result
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User byUserName = userService.getByUserName(user);
        if(byUserName!=null&&user.getPassword().equals(byUserName.getPassword())){

            String uuid = "user-token:" + UUID.randomUUID();
            redisTemplate.opsForValue().set(uuid, byUserName.getId().toString(),30,TimeUnit.MINUTES);
            System.out.println(redisTemplate.opsForValue().get(uuid));
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUserid(byUserName.getId());
            loginDTO.setToken(uuid);
            return Result.success(loginDTO);
        }
        return Result.fail();
    }

    /**
     * 通过id获取全部信息
     * @param id
     * @return Result
     */
    @GetMapping("{id}")
    public Result getUserById(@PathVariable Integer id){
        return Result.success(userService.getById(id));
    }

    /**
     * 修改用户信息
     * @param id
     * @param user
     * @return Result
     */
    @PutMapping("{id}")
    public Result updateInfoById(@PathVariable Integer id,@RequestBody User user){
        user.setId(id);
        userService.updateById(user);
        return Result.success();
    }

    @PutMapping("updatePassword/{id}")
    public Result updatePasswordById(@PathVariable Integer id, @RequestBody Password password){
        System.out.println(password.toString());
        if (userService.getById(id).getPassword().equals(password.getPassword())){
            User user=new User();
            user.setId(id);
            user.setPassword(password.getNewPassword());
            userService.updateById(user);
            return Result.success("修改成功");
        }
        return Result.fail("原密码错误，修改失败");
    }
}

