package com.cuber.entity.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author cuber
 * @create 2022/4/5 20:27
 */
@Data
public class DataQueryVO {
    private String roadName;
    private List<String> direction;
    private Date startDate;
    private Date endDate;
}
