package springcloud.sample.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import springcloud.sample.order.pojo.entity.Order;

/**
 * 订单数据库访问接口
 *
 * @author shivexx
 * @createTime 2022-06-11
 */
@Mapper
@Repository
public interface OrderMapper extends BaseMapper<Order> {

}
