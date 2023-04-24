package com.wingflare.lib.mybatis.plus.plugin;


import com.wingflare.lib.standard.db.BaseDo;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Statement;
import java.util.List;

/**
 * @author naizui_ycx
 * @date {2022/2/28}
 * @description
 */
@Intercepts(@Signature(type = ResultSetHandler.class,//指定你要拦截是哪个对象
        method = "handleResultSets",//指定你要拦截的是哪个方法
        args = {Statement.class}))
public class ResultSetHandlerInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();

        if (result instanceof List) {
            for (Object item : (List)result) {
                if (item instanceof BaseDo) {
                    ((BaseDo) item).clearNewField();
                }
            }
        }

        return result;
    }

}
