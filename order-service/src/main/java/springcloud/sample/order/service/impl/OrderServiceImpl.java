package springcloud.sample.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.sample.springcloud.common.exception.BizException;
import org.sample.springcloud.common.result.Result;
import springcloud.sample.order.enums.OrderStatus;
import springcloud.sample.order.mapper.OrderMapper;
import springcloud.sample.order.pojo.entity.Order;
import springcloud.sample.order.service.OrderService;
import org.sample.springcloud.payment.api.AccountApi;
import org.sample.springcloud.payment.pojo.dto.ReduceBalanceDTO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 订单服务接口实现类
 *
 * @author shive
 * @createTime 2022-06-11
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
        implements OrderService {

    @Autowired
    private AccountApi accountApi;

    @Override
    public boolean submit(List<BigInteger> productIds) {
        log.debug("submit order with productIds: {}", productIds);

        Order order = new Order();
        order.setUserid(this.getUserid());
        order.setAmount(this.getRandomAmount());
        order.setStatus(OrderStatus.PENDING_PAYMENT.getCode());
        return this.save(order);
    }

    /**
     * 获取用户编号
     */
    private BigInteger getUserid() {
        return new BigInteger("2018081225");
    }

    /**
     * 获取随机价格
     */
    private BigDecimal getRandomAmount() {
        double amount = ThreadLocalRandom.current().nextDouble(9999.99);
        return new BigDecimal(String.format("%.2f", amount));
    }

    @Override
    public boolean payment(BigInteger orderId) {
        Order order = this.getById(orderId);
        if (order == null) {
            throw new BizException("Order with id not exists");
        }
        if (OrderStatus.PAYED.getCode() == order.getStatus()) {
            throw new BizException("Order has already payed");
        }
        ReduceBalanceDTO reduceDTO = new ReduceBalanceDTO();
        reduceDTO.setAccountId(order.getUserid());
        reduceDTO.setTraderNo(orderId.toString());
        reduceDTO.setAmount(order.getAmount());

        Result<Boolean> result = accountApi.reduce(reduceDTO);
        log.info("accountApi return: {}", result);
        if (result.isSuccess()) {
            order.setStatus(OrderStatus.PAYED.getCode());
            return this.updateById(order);
        } else {
            throw new BizException("Reduce account '" + order.getUserid() + "' failed");
        }
    }
}
