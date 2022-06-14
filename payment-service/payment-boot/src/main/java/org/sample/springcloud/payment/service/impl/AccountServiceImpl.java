package org.sample.springcloud.payment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.sample.springcloud.common.exception.BizException;
import org.sample.springcloud.payment.mapper.AccountMapper;
import org.sample.springcloud.payment.pojo.entity.Account;
import org.sample.springcloud.payment.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 账户服务接口实现类
 *
 * @author shive
 * @createTime 2022-06-11
 */
@Slf4j
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
        implements AccountService {

    @Override
    public boolean reduce(BigInteger accountId, String traderNo, BigDecimal amount) {
        Account account = this.getById(accountId);
        if (account == null) {
            throw new BizException("Account with id '" + accountId + "' not exists");
        }
        if (account.getBalance().compareTo(amount) < 0) {
            log.info("trade '{}' of  account '{}' with amount '{}' payment failed, balance not enough", traderNo, accountId, account);
            throw new BizException("Balance not enough");
        }

        account.setBalance(account.getBalance().subtract(amount));
        return this.updateById(account);
    }
}
