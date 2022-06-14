package org.sample.springcloud.payment.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 日期字段自动填充组件
 *
 * @author shive
 * @createTime 2021-06-11
 */
@Component
public class DateMetaObjectHandler implements MetaObjectHandler {
    /**
     * 填充创建时间
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtCreate", Date::new, Date.class);
        this.strictUpdateFill(metaObject, "gmtModify", Date::new, Date.class);
    }

    /**
     * 填充更新时间
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "gmtModify", Date::new, Date.class);
    }
}
