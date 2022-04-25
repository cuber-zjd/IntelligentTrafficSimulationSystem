package com.cuber.entity.vo;

import lombok.Data;

/**
 * @author cuber
 * @create 2022/3/29 14:49
 */
@Data
public class DataVO extends com.cuber.entity.Data {

    public DataVO(com.cuber.entity.Data data){
        super(data);
    }

    private String roadName;

    private String paramName;

    private String sourceName;
}
