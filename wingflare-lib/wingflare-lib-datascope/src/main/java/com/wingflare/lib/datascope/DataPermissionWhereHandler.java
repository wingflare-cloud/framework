package com.wingflare.lib.datascope;


import com.wingflare.api.core.Ctx;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.datascope.entity.Condition;
import com.wingflare.lib.datascope.entity.DataPermissionData;
import com.wingflare.lib.datascope.utils.DataScopeUtil;
import com.wingflare.lib.mybatis.plus.plugin.handler.SelectWhereHandlerInterface;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 数据权限处理器
 *
 * @author shaoyuyao
 * @date 2022/8/18 17:02
 */
@Component
@ConditionalOnBean({
        DataScopeUtil.class
})
public class DataPermissionWhereHandler implements SelectWhereHandlerInterface {


    public Expression getSqlSegment(String mappedStatementId) {
        DataPermissionData dataPermissionData = ContextHolder.get(
                Ctx.DATA_PERMISSION_CONTEXT, DataPermissionData.class);
        if (dataPermissionData == null || dataPermissionData.isDisable()) {
            return null;
        }

        Map<String, Condition> conditions = dataPermissionData.getConditions();
        if (CollectionUtil.isEmpty(conditions) || !conditions.containsKey(mappedStatementId)) {
            return null;
        }

        Map<String, List<String>> whitelists = dataPermissionData.getWhitelists();
        Map<String, List<String>> blacklists = dataPermissionData.getBlacklists();

        if (CollectionUtil.isEmpty(whitelists) && CollectionUtil.isEmpty(blacklists)) {
            return null;
        }

        Condition condition = conditions.get(mappedStatementId);

        if (condition == null) {
            // 1 != 1
            return new NotEqualsTo(new Column("1"), new LongValue(1));
        }

        // 生成数据权限SQL表达式
        return DataScopeUtil.conditionToExpression(condition, whitelists, blacklists);
    }


    @Override
    public Expression setWhere(Expression whereExp, String mappedStatementId, Map parameter) {
        Expression expression = getSqlSegment(mappedStatementId);

        if (expression != null) {
            if (whereExp != null) {
                whereExp = new AndExpression(whereExp, expression);
            } else {
                whereExp = expression;
            }
        }

        return whereExp;
    }
}
