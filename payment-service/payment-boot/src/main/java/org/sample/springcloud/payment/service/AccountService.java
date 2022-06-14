package org.sample.springcloud.payment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.sample.springcloud.payment.pojo.entity.Account;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 账户服务接口
 *
 * @author shive
 * @createTime 2022-06-11
 */
public interface AccountService extends IService<Account> {
    /**
     * 扣减账户余额
     *
     * @param accountId 账户编号
     * @param traderNo  交易编号
     * @param amount    金额
     * @return 是否成功
     */
    boolean reduce(BigInteger accountId, String traderNo, BigDecimal amount);
}
