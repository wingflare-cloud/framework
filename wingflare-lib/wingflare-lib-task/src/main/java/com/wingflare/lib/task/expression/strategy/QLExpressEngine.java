package com.wingflare.lib.task.expression.strategy;

import com.wingflare.lib.task.exception.SnailJobCommonException;
import com.wingflare.lib.task.util.JsonUtil;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.config.QLExpressRunStrategy;

import javax.naming.InitialContext;
import java.util.Map;

/**
 * QL表达式解析器
 *
 * @author opensnail
 * @date 2023-09-10 17:40:34
 * @since 2.3.0
 */
public class QLExpressEngine extends AbstractExpressionEngine {

    private static final ExpressRunner ENGINE = new ExpressRunner();

    static {
        // fix => https://gitee.com/aizuda/snail-job/issues/ICNUG0
        QLExpressRunStrategy.addSecurityRiskMethod(InitialContext.class, "doLookup");
    }

    @Override
    public Object doEval(String expression, Map<String, Object> context) {

        final DefaultContext<String, Object> defaultContext = new DefaultContext<>();
        defaultContext.putAll(context);
        try {
            return ENGINE.execute(expression, defaultContext, null, true, false);
        } catch (Exception e) {
            throw new SnailJobCommonException("QL expression parsing exception. expression:[{}] context:[{}]",
                    expression, JsonUtil.toJsonString(context), e);
        }

    }


}
