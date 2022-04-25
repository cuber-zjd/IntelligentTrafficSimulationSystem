package com.cuber.controller;


import com.cuber.entity.DetectSetting;
import com.cuber.entity.vo.DetectParamQueryVO;
import com.cuber.service.IDetectSettingService;
import com.cuber.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cuber
 * @since 2022-04-06
 */
@RestController
@RequestMapping("/detectSettings")
public class DetectSettingController {

    @Autowired
    private IDetectSettingService detectSettingService;

    @PostMapping()
    public Result save(@RequestBody DetectSetting detectSetting) {
        return Result.success(detectSettingService.save(detectSetting));
    }

    @PutMapping()
    public Result update(@RequestBody DetectSetting detectSetting) {
        return Result.success(detectSettingService.updateById(detectSetting));
    }

    @PostMapping("/getPageList")
    public Result getPageList(@RequestBody DetectParamQueryVO queryVO) {
        return Result.success(detectSettingService.getPageList(queryVO));
    }

    /**
     * 根据id获取
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Integer id) {
        return Result.success(detectSettingService.getById(id));
    }

    /**
     * 根据id删除
     * @param id
     * @return Result
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        return Result.success(detectSettingService.removeById(id));
    }

    @GetMapping("/getByUserId/{userId}")
    public Result getByUserId(@PathVariable("userId") Integer userId) {
        return Result.success(detectSettingService.getByUserId(userId));
    }

}

