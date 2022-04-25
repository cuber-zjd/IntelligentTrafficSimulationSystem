package com.cuber.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author cuber
 * @since 2022-04-04
 */
@Data
public class Dataandspeed implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer dataid;

    private Double speed;

    private Timestamp passTime;

    public Dataandspeed(Integer dataid, Double speed, Timestamp passTime) {
        this.dataid = dataid;
        this.speed = speed;
        this.passTime = passTime;
    }
}
