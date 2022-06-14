package org.sample.springcloud.common.result;

import lombok.Data;

/**
 * 通用返回结果
 *
 * @author shive
 * @createTime 2022-01-17
 */
@Data
public class Result<T> {
    /** 状态码 */
    private String code;
    /** 响应数据 */
    private T data;
    /** 提示信息 */
    private String message;

    public static <T> Result<T> success() {
        return success(null, ResultCode.SUCCESS.getMessage());
    }

    public static <T> Result<T> success(T data) {
        return success(data, ResultCode.SUCCESS.getMessage());
    }

    public static <T> Result<T> success(T data, String message) {
        Result<T> result = new Result<>();
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> error() {
        return error(ResultCode.ERROR, ResultCode.ERROR.getMessage());
    }

    public static <T> Result<T> error(String message) {
        return error(ResultCode.ERROR, message);
    }

    public static <T> Result<T> error(IResultCode code) {
        return error(code, code.getMessage());
    }

    public static <T> Result<T> error(IResultCode code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code.getCode());
        result.setData(null);
        result.setMessage(message);
        return result;
    }

    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return ResultCode.SUCCESS.getCode().equals(this.code);
    }
}
