package org.sample.springcloud.common.result;

/**
 * 状态码接口
 *
 * @author shivexx
 * @createTime 2022-01-17
 */
public interface IResultCode {
    /**
     * 获取状态码
     *
     * @return 状态码
     */
    String getCode();

    /**
     * 获取状态码描述
     *
     * @return 状态码描述
     */
    String getMessage();
}
