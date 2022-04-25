package com.cuber.controller;




import com.cuber.entity.Road;
import com.cuber.entity.vo.RoadQueryVO;
import com.cuber.service.IRoadService;
import com.cuber.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cuber
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/roads")
public class RoadController {

    @Autowired
    private IRoadService roadService;

    /**
     * 分页按条件查询当前用户添加的全部道路
     * @return
     */
    @PostMapping("/getByUser/{userid}/{current}")
    public Result getPageList(@PathVariable Integer userid, @PathVariable Integer current, @RequestBody RoadQueryVO roadQueryVO){
        return Result.success(roadService.getPageListByUserId(userid,current, roadQueryVO));
    }

    /**
     * 查询对应id的道路
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id){
        return Result.success(roadService.getById(id));
    }

    /**
     * 修改道路信息
     * @param road
     * @return
     */
    @PutMapping()
    public Result update(@RequestBody Road road){
        return Result.success(roadService.updateById(road));
    }

    /**
     * 删除对应道路
     * @param id
     * @return Result
     */
    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        roadService.removeById(id);
        return Result.success();
    }

    @PostMapping()
    public Result save(@RequestBody Road road){
        roadService.save(road);
        return Result.success();
    }

    @GetMapping("allByUserId/{userid}")
    public Result getAllByUserId(@PathVariable Integer userid){
        return Result.success(roadService.getAllByUserId(userid));
    }
}

