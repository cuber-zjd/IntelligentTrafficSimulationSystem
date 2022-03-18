package com.cuber.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author cuber
 * @since 2022-02-27
 */
@lombok.Data
public class Data implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Timestamp starttime;

    private Timestamp endtime;

    private Integer num;

    private Double avgspeed;

    private Double maxspeed;

    private Double minspeed;

    private Double occupancy;

    private Integer sectionid;

    private String direction;
}
