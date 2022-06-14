package springcloud.sample.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.sample.springcloud.common.exception.BizException;
import org.sample.springcloud.common.result.Result;

/**
 * webmvc config
 *
 * @author shive
 * @createTime 2022-06-11
 */
@Configuration
public class WebMvcConfig {
    @RestControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(BizException.class)
        public Result<?> handleGlobalException(BizException e) {
            return Result.error(e.getMessage());
        }
    }
}
