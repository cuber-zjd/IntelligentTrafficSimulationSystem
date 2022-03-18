package com.cuber.controller.utils;

import lombok.Data;

/**
 * @author cuber
 * @create 2022/2/22 17:27
 */
@Data
public class R {
    private Boolean flag;
    private Object data;
    private String msg;

    public R(String msg) {
        this.flag = false;
        this.msg = msg;
    }

    public R() {
    }

    public R(Boolean flag) {
        this.flag = flag;
    }

    public R(Boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public R(Boolean flag, Object data, String msg) {
        this.flag = flag;
        this.data = data;
        this.msg = msg;
    }
}
