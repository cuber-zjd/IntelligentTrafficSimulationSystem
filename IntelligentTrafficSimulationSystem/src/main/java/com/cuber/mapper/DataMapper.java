package com.cuber.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cuber.entity.Data;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuber.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cuber
 * @since 2022-02-27
 */
@Mapper
public interface DataMapper extends BaseMapper<Data> {

//    public List<User> selectUserList(Page page, Integer id);

}
