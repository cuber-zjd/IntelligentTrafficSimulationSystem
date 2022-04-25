package com.cuber.entity.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author cuber
 * @create 2022/3/31 18:20
 */
@Data
public class AutoAddVO {
    private Integer userid;
    private Integer roadid;
    private Date date;
    private String direction;
    private String detectDirection;
    private Integer paramId;
}
