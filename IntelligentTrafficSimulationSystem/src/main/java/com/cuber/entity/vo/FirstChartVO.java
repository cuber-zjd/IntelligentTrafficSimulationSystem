package com.cuber.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author cuber
 * @create 2022/4/8 22:40
 */
@Data
public class FirstChartVO {
    private Integer roadId;
    private Date startTime;
    private Date endTime;
    private Integer segmentValue;
}
