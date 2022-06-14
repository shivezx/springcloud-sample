package org.sample.springcloud.payment.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 账户信息实体类
 *
 * @author shive
 * @createTime 2022-06-11
 */
@Data
@TableName("ums_account")
public class Account {
    /** 账户编号 */
    @TableId(type = IdType.AUTO)
    private BigInteger id;

    /** 账户余额 */
    private BigDecimal balance;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModify;

    /** 是否删除 */
    @TableLogic
    @TableField("is_deleted")
    private boolean deleted;

    /** 乐观锁版本号 */
    @Version
    private Integer version;
}
