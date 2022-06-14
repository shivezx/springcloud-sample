package springcloud.sample.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import springcloud.sample.order.pojo.entity.Order;

import java.math.BigInteger;
import java.util.List;


/**
 * 订单服务接口
 *
 * @author shivexx
 * @createTime 2022-06-11
 */
public interface OrderService extends IService<Order> {
    /**
     * 提交订单
     *
     * @param productIds 订单编号列表
     * @return 是否提交成功
     */
    boolean submit(List<BigInteger> productIds);

    /**
     * 支付订单
     *
     * @param orderId 订单编号
     * @return 是否支付成功
     */
    boolean payment(BigInteger orderId);
}
