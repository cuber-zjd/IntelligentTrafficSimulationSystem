package com.cuber.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author cuber
 * @since 2022-04-06
 */
@Data
@TableName("detect_setting")
public class DetectSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userid;

    private Double lineLength;

    private Double twoLineDistant;

    private Double linePosx;

    private Double linePosy;

    private Double distant;

    private String name;

}
