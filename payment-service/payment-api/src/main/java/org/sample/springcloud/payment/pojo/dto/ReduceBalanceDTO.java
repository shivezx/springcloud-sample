package org.sample.springcloud.payment.pojo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 扣减账户余额数据传输对象
 *
 * @author shive
 * @createTime 2022-06-11
 */
@Data
public class ReduceBalanceDTO {
    /** 账户编号 */
    private BigInteger accountId;
    /** 交易编号 */
    private String traderNo;
    /** 扣减金额 */
    private BigDecimal amount;
}
