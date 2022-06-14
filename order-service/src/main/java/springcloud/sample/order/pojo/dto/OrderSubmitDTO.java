package springcloud.sample.order.pojo.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

/**
 * 订单提交数据传输对象
 *
 * @author shive
 * @createTime 2022-06-11
 */
@Data
public class OrderSubmitDTO {
    /**
     * 商品编号列表
     */
    private List<BigInteger> productIds;
}
