package com.cuber.mapper;

import com.cuber.entity.Road;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuber.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cuber
 * @since 2022-03-27
 */
@Mapper
public interface RoadMapper extends BaseMapper<Road> {

}
