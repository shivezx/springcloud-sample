package org.sample.springcloud.common.exception;

/**
 * 业务异常
 *
 * @author shive
 * @createTime 2022-06-11
 */
public class BizException extends RuntimeException {
    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }
}
