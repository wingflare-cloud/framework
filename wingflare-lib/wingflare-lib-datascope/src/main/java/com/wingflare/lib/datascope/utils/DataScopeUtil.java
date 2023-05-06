package com.wingflare.lib.datascope.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wingflare.lib.standard.Ctx;
import com.wingflare.lib.core.context.ContextHolder;
import com.wingflare.lib.standard.enums.AuthType;
import com.wingflare.lib.core.utils.CollectionUtil;
import com.wingflare.lib.standard.utils.SecurityUtil;
import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.datascope.DataScopeHandle;
import com.wingflare.lib.datascope.entity.Condition;
import com.wingflare.lib.datascope.entity.DataPermissionData;
import com.wingflare.lib.datascope.entity.DpBindingData;
import com.wingflare.lib.datascope.entity.LogicalPayload;
import com.wingflare.lib.datascope.entity.Operator;
import com.wingflare.lib.datascope.parser.expression.IAndExpression;
import com.wingflare.lib.datascope.parser.expression.IOrExpression;
import com.wingflare.lib.security.enums.Logical;
import com.wingflare.lib.security.utils.AuthUtil;
import com.wingflare.lib.security.utils.UserAuthUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.expression.operators.relational.NotEqualsTo;
import net.sf.jsqlparser.schema.Column;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 数据权限工具类
 *
 * @author shaoyuyao
 * @date 2022/8/24 18:15
 */
@Component
@ConditionalOnBean({
        AuthUtil.class,
        DataScopeHandle.class
})
public class DataScopeUtil {

    @Resource
    private DataScopeHandle dataScopeHandle;

    @Resource
    private UserAuthUtil userAuthUtil;

    private static final Logger logger = LoggerFactory.getLogger(DataScopeUtil.class);

    /**
     * 从线程上下文数据中获取数据权限数据
     */
    public static DataPermissionData getDataPermissionData() {
        return ContextHolder.get(
                Ctx.DATA_PERMISSION_CONTEXT, DataPermissionData.class
        );
    }

    /**
     * 禁用数据权限
     */
    public static void disableDataPermission(Runnable runnable) {
        // 从线程上下文数据中获取数据权限数据
        DataPermissionData dataPermissionData = getDataPermissionData();
        if (null != dataPermissionData) {
            // 禁用数据权限
            dataPermissionData.setDisable(true);
        }
        runnable.run();
        if (null != dataPermissionData) {
            // 打开数据权限
            dataPermissionData.setDisable(false);
        }
    }

    /**
     * 设置数据权限
     *
     * @param dpBindingDataList 数据权限标识和Mapper全限定方法名绑定关系
     */
    public void setDataPermission(List<DpBindingData> dpBindingDataList) {
        // 获取数据权限标识和Mapper全限定方法名绑定关系
        if (CollectionUtil.isEmpty(dpBindingDataList)) {
            return;
        }

        if (SecurityUtil.getAuthMode() != null) {
            if (SecurityUtil.getAuthMode().equals(AuthType.USER)) {
                // 判断登录用户是否为超管，超管不用做数据权限处理
                Boolean isSuperAdmin = userAuthUtil.getUser() != null ? userAuthUtil.getUser().isSuperAdmin() : Boolean.FALSE;
                if (Boolean.TRUE.equals(isSuperAdmin)) {
                    return;
                }
            }

            DataPermissionData dataPermissionData = new DataPermissionData();
            setCondition(dpBindingDataList, dataPermissionData);
            setWhiteListAndBlackList(dataPermissionData);

            // 将数据权限数据放入线程上下文数据中
            ContextHolder.set(Ctx.DATA_PERMISSION_CONTEXT, dataPermissionData);
        }
    }

    /**
     * 给数据权限数据对象设置Mapper全限定方法名和数据权限条件过滤规则信息
     *
     * @param dpBindingDataList  数据权限标识和Mapper全限定方法名绑定关系
     * @param dataPermissionData 数据权限数据对象
     */
    private void setCondition(List<DpBindingData> dpBindingDataList, DataPermissionData dataPermissionData) {
        Map<String, Condition> conditions = new HashMap<>(dpBindingDataList.size());

        for (DpBindingData dpBindingData : dpBindingDataList) {
            if (StringUtil.isEmpty(dpBindingData.getConditionName())
                    || StringUtil.isEmpty(dpBindingData.getMappedStatementId())) {
                continue;
            }

            // 从redis中拿到数据权限条件过滤规则
            String conditionJsonString = dataScopeHandle.getCondition(
                    String.format(
                            "%s:%s", Ctx.PREFIX_DATA_PERMISSION_CONDITION, dpBindingData.getConditionName())
            );

            if (StringUtil.isEmpty(conditionJsonString)) {
                conditionJsonString = dataScopeHandle.getCondition(
                        String.format(
                                "%s:%s",
                                Ctx.PREFIX_DATA_PERMISSION_CONDITION,
                                Ctx.BASE_DATA_CONDITION_NAME
                        )
                );
            }

            if (StringUtil.isEmpty(conditionJsonString)) {
                conditions.put(dpBindingData.getMappedStatementId(), null);
                continue;
            }

            JSONObject conditionJson = JSONObject.parseObject(conditionJsonString);
            Condition condition = parseJsonToCondition(conditionJson);
            conditions.put(dpBindingData.getMappedStatementId(), condition);
        }

        if (CollectionUtil.isNotEmpty(conditions)) {
            dataPermissionData.setConditions(conditions);
        }
    }

    /**
     * 给数据权限数据对象设置白名单和黑名单信息
     *
     * @param dataPermissionData 数据权限数据对象
     */
    private void setWhiteListAndBlackList(DataPermissionData dataPermissionData) {
        String systemCode = SecurityUtil.getBusinessSystem();
        String str;

        if (SecurityUtil.getAuthMode().equals(AuthType.USER)) {
            str = "user:" + SecurityUtil.getUserId();
        } else {
            str = "app:" + SecurityUtil.getAppId();
        }

        Map<String, List<String>> whitelists = dataScopeHandle.getWhitelist(
                String.format(
                        "%s:%s:whiteList:%s",
                        systemCode, Ctx.PREFIX_DATA_PERMISSION_KEY, str)
        );

        Map<String, List<String>> blacklists = dataScopeHandle.getWhitelist(
                String.format(
                        "%s:%s:blackList:%s",
                        systemCode, Ctx.PREFIX_DATA_PERMISSION_KEY, str)
        );

        dataPermissionData.setWhitelists(whitelists);
        dataPermissionData.setBlacklists(blacklists);
    }

    private Condition parseJsonToCondition(JSONObject jsonObject) {
        if (!jsonObject.containsKey("payloadList") || StringUtil.isEmpty(jsonObject.getString("payloadList"))) {
            return null;
        }

        Condition condition = new Condition();
        setLogical(condition, jsonObject.getString("logical"));
        JSONArray payloadList = jsonObject.getJSONArray("payloadList");

        if (StringUtil.isNotEmpty(jsonObject.getString("filterExp"))) {
            condition.setFilterExp(jsonObject.getString("filterExp"));
        }

        payloadList.forEach(item -> {
            JSONObject oi = (JSONObject) item;
            LogicalPayload payload = new LogicalPayload();
            String type = oi.getString("type");
            if (Ctx.DP_PAYLOAD_TYPE_C.equals(type)) {
                payload.setType(Ctx.DP_PAYLOAD_TYPE_C);
                payload.setPayload(parseJsonToCondition(oi.getJSONObject("payload")));
            } else if (Ctx.DP_PAYLOAD_TYPE_O.equals(type)) {
                payload.setType(Ctx.DP_PAYLOAD_TYPE_O);
                payload.setPayload(parseJsonToOperator(payload, oi.getJSONObject("payload")));
            }

            setLogical(payload, oi.getString("logical"));
            condition.getPayloadList().add(payload);
        });

        return condition;
    }

    public static void setLogical(Object o, String logical) {
        Logical l;

        if (Ctx.DP_LOGICAL_OR.equalsIgnoreCase(logical)) {
            l = Logical.OR;
        } else {
            l = Logical.AND;
        }

        if (o instanceof Condition) {
            ((Condition) o).setLogical(l);
        } else if (o instanceof LogicalPayload) {
            ((LogicalPayload) o).setLogical(l);
        }
    }

    private Operator parseJsonToOperator(LogicalPayload payload, JSONObject jsonObject) {
        Operator operator = new Operator(jsonObject.getString("type"));
        operator.setContextName(jsonObject.getString("contextName"));
        operator.setFieldName(jsonObject.getString("fieldName"));
        setLogical(payload, jsonObject.getString("logical"));
        return operator;
    }

    public static Expression conditionToExpression(Condition condition, Map<String, List<String>> whitelists, Map<String, List<String>> blacklists) {
        Expression expression = null;

        if (StringUtil.isNotEmpty(condition.getFilterExp())) {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
            ContextHolder.getLocalMap().forEach(engine::put);
            engine.put("whitelists", whitelists);
            engine.put("blacklists", blacklists);
            try {
                engine.eval(condition.getFilterExp());
            } catch (Throwable e) {
                logger.error(e.getMessage());
                logger.error(ExceptionUtils.getStackTrace(e));
            }
        }

        for (LogicalPayload logicalPayload : condition.getPayloadList()) {

            if (Ctx.DP_PAYLOAD_TYPE_C.equals(logicalPayload.getType())) {
                Condition partCondition = (Condition) logicalPayload.getPayload();
                Expression partExpression = conditionToExpression(partCondition, whitelists, blacklists);

                if (partExpression == null) {
                    continue;
                }

                if (expression == null) {
                    expression = partExpression;
                } else {
                    expression = logicalPayload.getLogical() == Logical.OR ?
                            new IOrExpression(expression, partExpression) : new IAndExpression(expression, partExpression);
                }

            } else if (Ctx.DP_PAYLOAD_TYPE_O.equals(logicalPayload.getType())) {

                Expression partExpression = operatorToExpression(logicalPayload, whitelists, blacklists);

                if (partExpression == null) {
                    continue;
                }

                if (expression == null) {
                    expression = partExpression;
                } else {
                    expression = logicalPayload.getLogical() == Logical.OR ?
                            new IOrExpression(expression, partExpression) : new IAndExpression(expression, partExpression);
                }

            }

        }

        return expression;
    }

    private static Expression operatorToExpression(LogicalPayload logicalPayload, Map<String, List<String>> whitelists, Map<String, List<String>> blacklists) {
        Operator operator = (Operator) logicalPayload.getPayload();
        List<String> whitelist = whitelists.get(operator.getContextName());
        List<String> blacklist = blacklists.get(operator.getContextName());

        boolean whitelistIsEmpty = CollectionUtil.isEmpty(whitelist);
        boolean blacklistIsEmpty = CollectionUtil.isEmpty(blacklist);

        if (whitelistIsEmpty && blacklistIsEmpty) {
            return null;
        }

        Column column = new Column(operator.getFieldName());

        switch (operator.getOperator()) {

            case Operator.IS: {
                if (!whitelistIsEmpty) {
                    if (whitelist.size() == 1) {
                        return new EqualsTo(column, new StringValue(whitelist.get(0)));
                    }

                    return new InExpression(
                            column,
                            new ExpressionList(whitelist.stream().map(StringValue::new).collect(Collectors.toList()))
                    );
                }
            }

            case Operator.NOT: {

                if (!blacklistIsEmpty) {
                    if (blacklist.size() == 1) {
                        return new NotEqualsTo(column, new StringValue(blacklist.get(0)));
                    }

                    return new InExpression(
                            column,
                            new ExpressionList(blacklist.stream().map(StringValue::new).collect(Collectors.toList()))
                    ).withNot(true);
                }

            }

            case Operator.LIKE: {
                if (!whitelistIsEmpty) {
                    if (whitelist.size() == 1) {
                        return newLikeExpression(column, whitelist.get(0), false);
                    }

                    Expression expression = null;
                    for (String value : whitelist) {
                        if (expression == null) {
                            expression = newLikeExpression(column, value, false);
                        } else {
                            expression = new IOrExpression(expression, newLikeExpression(column, value, false));
                        }
                    }
                    return expression;
                }
            }

            case Operator.NO_LIKE: {
                if (!blacklistIsEmpty) {
                    if (blacklist.size() == 1) {
                        return newLikeExpression(column, blacklist.get(0), true);
                    }

                    Expression expression = null;
                    for (String value : blacklist) {
                        if (expression == null) {
                            expression = newLikeExpression(column, value, true);
                        } else {
                            expression = new IOrExpression(expression, newLikeExpression(column, value, true));
                        }
                    }
                    return expression;
                }
            }
            default: {
                return null;
            }
        }
    }

    private static LikeExpression newLikeExpression(Column column, String value, boolean isNot) {
        LikeExpression likeExpression = new LikeExpression();
        likeExpression.setLeftExpression(column);
        likeExpression.setRightExpression(new StringValue("%" + value + "%"));
        likeExpression.setNot(isNot);
        return likeExpression;
    }

}
