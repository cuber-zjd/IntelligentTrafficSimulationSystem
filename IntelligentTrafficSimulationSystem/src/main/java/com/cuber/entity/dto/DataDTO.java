package com.cuber.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cuber.entity.Road;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author cuber
 * @create 2022/3/29 14:26
 */
@lombok.Data
public class DataDTO {
        private static final long serialVersionUID = 1L;

        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

        private Timestamp starttime;

        private Timestamp endtime;

        private Integer num;

        private Double avgspeed;

        private Double maxspeed;

        private Double minspeed;

        private Integer roadid;

        private String direction;

        private Integer userid;


}
