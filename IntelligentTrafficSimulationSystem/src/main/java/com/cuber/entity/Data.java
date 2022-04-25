package com.cuber.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

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

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp starttime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp endtime;

    private Integer roadid;

    private String direction;

    private Integer userid;

    private int paramId;

    private int source;

    public Data(Data data) {
        this.id = data.id;
        this.starttime = data.starttime;
        this.endtime = data.endtime;
        this.roadid = data.roadid;
        this.direction = data.direction;
        this.userid = data.userid;
        this.paramId = data.paramId;
        this.source = data.source;
    }

    public Data() {
    }
}
