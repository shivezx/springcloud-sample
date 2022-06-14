package org.sample.springcloud.payment.config;

import org.sample.springcloud.common.exception.BizException;
import org.sample.springcloud.common.result.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * webmvc config
 *
 * @author shive
 * @createTime 2022-06-11
 */
@Configuration
public class WebMvcConfig {
    /**
     * 全局异常处理
     */
    @RestControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(BizException.class)
        public Result<?> handleGlobalException(BizException e) {
            return Result.error(e.getMessage());
        }
    }
}
