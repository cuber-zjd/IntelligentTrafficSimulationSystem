package com.cuber.controller;


import com.cuber.entity.LightParam;
import com.cuber.entity.vo.LightParamVO;
import com.cuber.service.ILightParamService;
import com.cuber.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cuber
 * @since 2022-04-26
 */
@RestController
@RequestMapping("/lightParams")
public class LightParamController {

    @Autowired
    private ILightParamService lightParamService;

    @PostMapping("getAll")
    public Result getAll(@RequestBody LightParamVO lightParamVO){
        return Result.success(lightParamService.getPageList(lightParamVO));
    }

    @GetMapping("{id}")
    public Result getById(@PathVariable int id){
        return Result.success(lightParamService.getById(id));
    }

    @GetMapping("/getAllByUserId/{userid}")
    public Result getByUserId(@PathVariable int userid){
        return Result.success(lightParamService.getByUserId(userid));
    }

    @PostMapping()
    public Result save(@RequestBody LightParam lightParam){
        return Result.success(lightParamService.save(lightParam));
    }

    @PutMapping()
    public Result update(@RequestBody LightParam lightParam){
        return Result.success(lightParamService.updateById(lightParam));
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable int id){
        return Result.success(lightParamService.removeById(id));
    }

}

