package com.cuber.controller;


import com.cuber.controller.utils.R;
import com.cuber.entity.Intersection;
import com.cuber.service.IIntersectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cuber
 * @since 2022-02-27
 */
@RestController
@RequestMapping("/intersections")
public class IntersectionController {

    @Autowired
    private IIntersectionService intersectionService;


    /**
     * 添加路口
     * @param intersection
     * @return
     */
    @PostMapping()
    public R save(@RequestBody Intersection intersection){
        return new R(intersectionService.save(intersection),null,"添加成功");
    }

    /**
     * 查询当前用户添加的全部路口
     * @return
     */
    @GetMapping("/getByUser/{userid}")
    public R getAll(@PathVariable Integer userid){
        return new R(true,intersectionService.getAllByUserId(userid),"查询成功");
    }

    @GetMapping("{id}")
    public R getById(@PathVariable Integer id){
        return new R(true,intersectionService.getById(id),"查询成功");
    }

    @PutMapping()
    public R update(@RequestBody Intersection intersection){
        return new R(intersectionService.updateById(intersection));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id){
        return new R(intersectionService.removeById(id));
    }

    @GetMapping("/getByName")
    public R getByName(@RequestParam String name){
        return new R(true,intersectionService.getByName(name));
    }


}

