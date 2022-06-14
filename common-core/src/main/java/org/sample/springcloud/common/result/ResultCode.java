package org.sample.springcloud.common.result;

/**
 * 状态码枚举类
 *
 * @author shivexx
 * @createTime 2022-01-17
 */
public enum ResultCode implements IResultCode {
    /** 操作成功 */
    SUCCESS("00000", "Operation Success"),
    /** 操作失败 */
    ERROR("B0001", "Operation Failed");

    private final String code;
    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "{" + "\"code\":\"" + code + '\"' + ", \"msg\":\"" + message + '\"' + '}';
    }
}
