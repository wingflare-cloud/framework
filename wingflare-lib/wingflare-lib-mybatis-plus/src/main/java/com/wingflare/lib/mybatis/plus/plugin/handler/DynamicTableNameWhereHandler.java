package com.wingflare.lib.mybatis.plus.plugin.handler;


import com.wingflare.api.core.Ctx;
import com.wingflare.lib.mybatis.plus.bo.DynamicTableNameBuffer;
import com.wingflare.lib.mybatis.plus.utils.DynamicTableNameUtil;
import com.wingflare.lib.core.context.ContextHolder;
import net.sf.jsqlparser.expression.Expression;

import java.util.Map;

/**
 * 动态表名处理器
 *
 * @author shaoyuyao
 * @date 2023/3/10 17:31
 */
public class DynamicTableNameWhereHandler implements SelectWhereHandlerInterface {

    public String sql(String sql, Object parameter) {
        // 获取动态表名数据
        DynamicTableNameBuffer buffer = ContextHolder.get(
                Ctx.DYNAMIC_TABLE_NAME_CONTEXT, DynamicTableNameBuffer.class
        );

        return DynamicTableNameUtil.replaceTableName(sql, buffer);
    }

    @Override
    public Expression setWhere(Expression whereExp, String mappedStatementId, Map parameter) {
        return whereExp;
    }
}
