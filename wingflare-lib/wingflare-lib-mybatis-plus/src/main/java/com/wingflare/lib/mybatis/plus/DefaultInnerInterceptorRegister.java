package com.wingflare.lib.mybatis.plus;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.plugin.inner.PageInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultInnerInterceptorRegister implements InnerInterceptorRegister {

    @Value("${db.type:}")
    private String dbType;

    @Override
    public List<InnerInterceptor> getInnerInterceptors() {
        return new ArrayList<InnerInterceptor>(){{
            add(new PageInterceptor(getDbType()));
            add(new OptimisticLockerInnerInterceptor());
        }};
    }

    public DbType getDbType() {
        if (StringUtil.isEmpty(dbType)) {
            return DbType.MYSQL;
        }

        return DbType.getDbType(dbType);
    }

}
