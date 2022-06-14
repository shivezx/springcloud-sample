package springcloud.sample.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.sample.springcloud.payment.api.AccountApi;

/**
 * 订单服务启动类
 *
 * @author shivexx
 * @createTime 2022-06-11
 */
@SpringBootApplication
@EnableFeignClients(basePackageClasses = {AccountApi.class})
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
