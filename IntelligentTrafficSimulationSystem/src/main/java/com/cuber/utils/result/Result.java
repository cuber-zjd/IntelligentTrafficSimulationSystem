package com.cuber.utils.result;

import lombok.Data;

/**
 *
 * 结果控制
 * @author cuber
 * @create 2022/3/19 18:03
 */
@Data
public class Result {
    /**
     * 状态码
     */
    private int code;
    /**
     * 信息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    /**
     * 空参构造方法
     */
    private Result() { }

    /**
     * 将数据填入result工具类，并加入状态码和相关消息
     * @param data
     * @param resultStatus
     * @return Result
     */
    public static Result build(Object data,ResultStatus resultStatus) {
        Result result=new Result();
        if(null!=data)
        {
            result.setData(data);
        }
        result.setCode(resultStatus.getCode());
        result.setMessage(resultStatus.getMessage());
//        System.out.println(result);
        return result;
    }

    /**
     * 请求成功调用的方法
     * @param data
     * @return Result
     */
    public static Result success(Object data) {
        return build(data,ResultStatus.SUCCESS);
    }

    /**
     * 请求成功调用的方法 （空参）
     * @return Result
     */
    public static Result success() {
        return Result.success(null);
    }

    /**
     * 请求失败调用的方法
     * @param data
     * @return Result
     */
    public static Result fail(Object data) {
        return build(data,ResultStatus.FAIL);
    }

    /**
     * 请求失败调用的方法 (空参)
     * @return Result
     */
    public static Result fail() {
        return Result.fail(null);
    }

}