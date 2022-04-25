package com.cuber.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author cuber
 * @create 2022/3/28 13:09
 */
@Data
public class RoadQueryVO {
    private String name;
    private List<String> roadTypes;
}
