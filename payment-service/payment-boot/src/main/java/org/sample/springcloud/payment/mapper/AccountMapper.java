package org.sample.springcloud.payment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.sample.springcloud.payment.pojo.entity.Account;
import org.springframework.stereotype.Repository;

/**
 * @author shive
 * @createTime 2022-06-11
 */
@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account> {

}
