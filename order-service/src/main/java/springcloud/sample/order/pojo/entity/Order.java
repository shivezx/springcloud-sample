package springcloud.sample.order.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * 订单实体类
 *
 * @author shivexx
 * @createTime 2022-06-11
 */
@Data
@TableName("oms_order")
public class Order {
    /** 订单编号 */
    @TableId(type = IdType.AUTO)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigInteger id;

    /** 用户编号 */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigInteger userid;

    /** 订单金额 */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal amount;

    /** 订单状态 */
    private Integer status;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    /** 修改时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModify;

    /** 逻辑删除 */
    @TableLogic
    @TableField("is_deleted")
    private boolean deleted;

    /** 乐观锁版本号 */
    @Version
    private Integer version;
}
