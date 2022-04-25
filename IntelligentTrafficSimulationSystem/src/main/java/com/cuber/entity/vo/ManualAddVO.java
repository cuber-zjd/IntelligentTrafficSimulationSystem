package com.cuber.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author cuber
 * @create 2022/4/5 23:57
 */
@Data
public class ManualAddVO {
    private Integer userid;
    private Integer roadid;
    private String direction;
    private Date startDate;
    private Date endDate;
    private Integer num;
    private Double maxSpeed;
    private Double minSpeed;
}
