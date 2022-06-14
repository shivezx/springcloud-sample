package springcloud.sample.order.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.sample.springcloud.common.exception.BizException;
import org.sample.springcloud.common.result.Result;
import springcloud.sample.order.pojo.dto.OrderSubmitDTO;
import springcloud.sample.order.pojo.entity.Order;
import springcloud.sample.order.service.OrderService;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 订单接口
 *
 * @author shivexx
 * @createTime 2022-06-11
 */
@Tag(name = "订单接口")
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    private final AtomicInteger counter = new AtomicInteger(0);

    @Autowired
    private OrderService orderService;

    /**
     * FlowRule 流控规则
     * 流控规则: FLOW_GRADE_QPS (QPS 限流), FLOW_GRADE_THREAD (并发线程数限流)
     * 控制效果: CONTROL_BEHAVIOR_DEFAULT (直接拒绝), CONTROL_BEHAVIOR_WARM_UP (预热, warmUpPeriodSec 预热时长), CONTROL_BEHAVIOR_RATE_LIMITER (匀速排队, maxQueueingTimeMs 排队时常)
     * 调用关系限流策略: STRATEGY_DIRECT (针对资源本身直接限流), STRATEGY_RELATE (关联限流), STRATEGY_CHAIN (链路限流)
     * <p>
     * DegradeRule 熔断规则
     * 熔断规则: DEGRADE_GRADE_EXCEPTION_RATIO (异常比例), DEGRADE_GRADE_EXCEPTION_COUNT (异常数), DEGRADE_DEFAULT_SLOW_REQUEST_AMOUNT (慢调用比例)
     */
    @PostConstruct
    private static void initFlowRules() {
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("QueryOrder");
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10); // 按照 qps 限流

        DegradeRule degradeRule = new DegradeRule();
        degradeRule.setResource("QueryOrder");
        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO); // 按照异常比例熔断
        degradeRule.setStatIntervalMs(10000); // 统计时长
        degradeRule.setCount(0.3); // 异常比例
        degradeRule.setTimeWindow(5); // 熔断时长

        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
        DegradeRuleManager.loadRules(Collections.singletonList(degradeRule));
    }

    @Operation(summary = "提交订单")
    @PostMapping("/submit")
    public Result<?> submit(@RequestBody OrderSubmitDTO submitDTO) {
        if (CollUtil.isEmpty(submitDTO.getProductIds())) {
            return Result.error("Please select product");
        }
        return orderService.submit(submitDTO.getProductIds()) ? Result.success() : Result.error();
    }

    @Operation(summary = "支付订单", parameters = {
            @Parameter(name = "orderId", description = "订单编号", in = ParameterIn.QUERY, required = true)
    })
    @PostMapping("/payment")
    public Result<?> payment(@RequestParam BigInteger orderId) {
        return orderService.payment(orderId) ? Result.success() : Result.error();
    }

    @Operation(summary = "订单信息", parameters = {
            @Parameter(name = "orderId", description = "订单编号", in = ParameterIn.PATH, required = true)
    })
    @GetMapping("/{orderId}")
    @SentinelResource(value = "QueryOrder", fallback = "fallbackHandler", blockHandler = "blockHandler")
    public Result<Order> getOrder(@PathVariable String orderId) {
        int count = counter.getAndIncrement();
        if (count % 2 == 0) {
            throw new BizException("BizException");
        }
        return Result.success(orderService.getById(orderId));
    }

    /**
     * 针对业务异常进行降级
     */
    @SuppressWarnings("unused")
    public Result<?> fallbackHandler(String orderId, Throwable throwable) {
        log.warn("query order with id '{}' throw exception:", orderId, throwable);
        return Result.error("query order with id '" + orderId + "' error, this is fallback message");
    }

    /**
     * 针对熔断与流量控制进行降级
     */
    @SuppressWarnings("unused")
    public Result<?> blockHandler(String orderId, BlockException e) {
        return Result.error("query order with id '" + orderId + "' blocked by sentinel");
    }
}
