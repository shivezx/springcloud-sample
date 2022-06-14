package org.sample.springcloud.payment.controller;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.sample.springcloud.common.result.Result;
import org.sample.springcloud.payment.pojo.dto.ReduceBalanceDTO;
import org.sample.springcloud.payment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 账户接口
 *
 * @author shive
 * @createTime 2022-06-11
 */
@Tag(name = "账户接口")
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Operation(summary = "减少账户余额")
    @PostMapping("/balance/reduce")
    public Result<Boolean> reduce(@RequestBody ReduceBalanceDTO reduceDTO) {
        if (reduceDTO.getAccountId() == null || StrUtil.isBlank(reduceDTO.getTraderNo()) || reduceDTO.getAmount() == null) {
            return Result.error("Required params is missing");
        }
        if (reduceDTO.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return Result.error("Amount can not be negative");
        }
        return accountService.reduce(reduceDTO.getAccountId(), reduceDTO.getTraderNo(), reduceDTO.getAmount())
                ? Result.success() : Result.error();
    }
}
