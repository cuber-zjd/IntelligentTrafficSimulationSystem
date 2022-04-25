package com.cuber.utils.result;


import lombok.Getter;

/**
 * 结果状态（枚举）
 * @author cuber
 * @create 2022/3/19 18:03
 */
@Getter
public enum ResultStatus {

    //成功
    SUCCESS(200, "成功"),
    //失败
    FAIL(201,"失败");


    private final int code;

    private final String message;

    /**
     * 结果状态的构造方法
     * @param code
     * @param message
     */
    private ResultStatus(int code, String message) {
        this.code=code;
        this.message=message;
    }
}
