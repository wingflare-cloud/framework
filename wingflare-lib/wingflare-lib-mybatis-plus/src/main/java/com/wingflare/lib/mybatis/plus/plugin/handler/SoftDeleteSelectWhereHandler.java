package com.wingflare.lib.mybatis.plus.plugin.handler;


import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.utils.SoftDeleteUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 软删除查询处理器
 *
 * @author shaoyuyao
 * @date 2022/8/18 15:45
 */
@Component
public class SoftDeleteSelectWhereHandler implements SelectWhereHandlerInterface {

    @Override
    public Expression setWhere(Expression whereExp, String whereSegment, Map parameter) {
        if (parameter == null) {
            return null;
        }

        // 该Map的Class类型为ParamMap，调用get方法如果map中不存在该key则直接抛BindingException
        if (!parameter.containsKey(Constant.DATA_SCOPE)) {
            return null;
        }

        Object dataScopeObj = parameter.get(Constant.DATA_SCOPE);
        if (dataScopeObj == null) {
            return null;
        }

        String dataScope = (String) dataScopeObj;
        if (SoftDeleteUtil.isAll(dataScope)) {
            return null;
        }

        Integer value = SoftDeleteUtil.getValue(dataScope);

        if (value != null) {
            EqualsTo equalsTo = new EqualsTo();
            equalsTo.setLeftExpression(new Column(Constant.IS_DELETE));
            equalsTo.setRightExpression(new LongValue(value));
            return equalsTo;
        }

        return null;
    }
}
