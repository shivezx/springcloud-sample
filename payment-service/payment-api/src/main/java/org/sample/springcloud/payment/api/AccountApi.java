package org.sample.springcloud.payment.api;

import org.sample.springcloud.common.result.Result;
import org.sample.springcloud.payment.pojo.dto.ReduceBalanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 账户信息 API
 *
 * @author shive
 * @createTime 2022-06-11
 */
@Primary
@FeignClient(value = "payment-service", fallback = AccountApi.AccountApiFallback.class)
public interface AccountApi {
    @PostMapping("/account/balance/reduce")
    Result<Boolean> reduce(@RequestBody ReduceBalanceDTO reduceDTO);

    @Component
    class AccountApiFallback implements AccountApi {
        @Override
        public Result<Boolean> reduce(ReduceBalanceDTO reduceDTO) {
            return Result.error("Service temporary unavailable");
        }
    }
}
