package com.wingflare.lib.datascope.utils;


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.wingflare.api.core.Ctx;
import com.wingflare.api.security.UserAuthServer;
import com.wingflare.api.security.enums.Logical;
import com.wingflare.lib.core.exceptions.BusinessLogicException;
import com.wingflare.lib.datascope.DPInfo;
import com.wingflare.lib.core.context.ContextHolder;
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

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
        UserAuthServer.class,
        DataScopeHandle.class
})
public class DataScopeUtil {

    private final DataScopeHandle dataScopeHandle;

    private final UserAuthServer userAuthServer;

    private static final Logger logger = LoggerFactory.getLogger(DataScopeUtil.class);

    public DataScopeUtil(DataScopeHandle dataScopeHandle, UserAuthServer userAuthServer) {
        this.dataScopeHandle = dataScopeHandle;
        this.userAuthServer = userAuthServer;
    }

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

        // 判断登录用户是否为超管，超管不用做数据权限处理
        Boolean isSuperAdmin = userAuthServer.getUser() != null ? userAuthServer.getUser().isSuperAdmin() : Boolean.FALSE;

        if (Boolean.TRUE.equals(isSuperAdmin)) {
            return;
        }

        DataPermissionData dataPermissionData = new DataPermissionData();
        setCondition(dpBindingDataList, dataPermissionData);
        setDPInfo(dataPermissionData);

        // 将数据权限数据放入线程上下文数据中
        ContextHolder.set(Ctx.DATA_PERMISSION_CONTEXT, dataPermissionData);
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
    private void setDPInfo(DataPermissionData dataPermissionData) {
        List<String> priorityExpression = dataScopeHandle.getPriorityExpression();

        if (CollectionUtil.isNotEmpty(priorityExpression)) {
            DPInfo dpInfo = null;
            for (String exp : priorityExpression) {
                if (dpInfo == null) {
                    dpInfo = getExpressionDPInfo(exp);
                } else {
                    DPInfo subDPInfo = getExpressionDPInfo(exp);
                    calculateDPInfo(dpInfo, subDPInfo);
                    mergeDPInfo(dpInfo.getWhitelist(), subDPInfo.getWhitelist());
                    mergeDPInfo(dpInfo.getBlacklist(), subDPInfo.getBlacklist());
                }
            }

            if (dpInfo != null) {
                dataPermissionData.setWhitelists(dpInfo.getWhitelist());
                dataPermissionData.setBlacklists(dpInfo.getBlacklist());
            }
        } else {
            dataPermissionData.setWhitelists(getDPInfo(true));
            dataPermissionData.setBlacklists(getDPInfo(false));
        }
    }

    /**
     * 通过表达式获取数据权限信息
     *
     * @param expression
     * @return
     */
    private DPInfo getExpressionDPInfo(String expression) {
        if (StringUtil.isBlank(expression)) {
            throw new BusinessLogicException("dp.expression.option.err");
        }

        String subType = expression;
        Object subId = null;
        DPInfo dpInfo = new DPInfo();
        int expIndex = expression.indexOf(":");

        if (expIndex != -1) {
            String[] strings = expression.split(":", 2);
            subType = strings[1];
            if ("ctx".equals(subType)) {
                subId = ContextHolder.get(subType);
            }
        }

        if (ObjectUtil.isEmpty(subId)) {
            dpInfo.setWhitelist(getDPInfo(true, subType, null));
            dpInfo.setBlacklist(getDPInfo(false, subType, null));
        } else if (subId instanceof String) {
            dpInfo.setWhitelist(getDPInfo(true, subType, (String) subId));
            dpInfo.setBlacklist(getDPInfo(false, subType, (String) subId));
        } else if (subId instanceof List) {
            List<String> list = (List<String>) subId;
            List<Map<String, List<String>>> whitelists = getDPInfoList(true, subType, list);
            List<Map<String, List<String>>> blacklists = getDPInfoList(false, subType, list);
            Map<String, List<String>> whitelist = whitelists.getFirst();
            Map<String, List<String>> blacklist = blacklists.getFirst();

            for (int i = 1; i < whitelist.size(); i++) {
                mergeDPInfo(whitelist, whitelists.get(i));
            }

            for (int i = 1; i < whitelist.size(); i++) {
                mergeDPInfo(blacklist, blacklists.get(i));
            }

            dpInfo.setWhitelist(whitelist);
            dpInfo.setBlacklist(blacklist);
        }

        return dpInfo;
    }

    /**
     * 父子集黑白名单数据对冲（实现子集白名单数据权限覆盖父级黑名单数据）
     *
     * @param dpInfo
     * @param subDpInfo
     */
    private void calculateDPInfo(DPInfo dpInfo, DPInfo subDpInfo) {
        if (CollectionUtil.isEmpty(dpInfo.getBlacklist()) || CollectionUtil.isEmpty(subDpInfo.getWhitelist())) {
            return;
        }

        Map<String, List<String>> whitelist = subDpInfo.getWhitelist();

        for (Map.Entry<String, List<String>> dp : dpInfo.getBlacklist().entrySet()) {
            if (whitelist.containsKey(dp.getKey())) {
                dp.getValue().removeAll(whitelist.get(dp.getKey()));
            }
        }
    }

    /**
     * 合并同类型数据权限
     *
     * @param dp1
     * @param dp2
     */
    private void mergeDPInfo(Map<String, List<String>> dp1, Map<String, List<String>> dp2) {
        List<String> processedKeys = new ArrayList<>();

        for (Map.Entry<String, List<String>> dp : dp1.entrySet()) {
            processedKeys.add(dp.getKey());

            if (dp2.containsKey(dp.getKey())) {
                dp1.get(dp.getKey()).addAll(dp2.get(dp.getKey()));
                dp1.put(dp.getKey(), new ArrayList<>(new HashSet<>(dp1.get(dp.getKey()))));
            }
        }

        for (Map.Entry<String, List<String>> dp : dp2.entrySet()) {
            if (processedKeys.contains(dp.getKey())) {
                break;
            }

            dp1.put(dp.getKey(), dp.getValue());
        }
    }

    /**
     * 获取数据权限信息
     *
     * @param isWhitelist 是否白名单
     * @param type        数据权限类型
     * @param typeId      数据权限业务id
     * @return
     */
    private Map<String, List<String>> getDPInfo(boolean isWhitelist, String type, String typeId) {
        String lastStr = String.format(":%s", SecurityUtil.getUser() != null ? SecurityUtil.getUser().getUserId() : "");

        if (StringUtil.isBlank(type)) {
            if (StringUtil.isBlank(typeId)) {
                lastStr = String.format(":%s:%s", type, typeId);
            } else {
                lastStr = String.format(":%s", type);
            }
        }

        return dataScopeHandle.getDPList(
                String.format(
                        "%s:%s:%s:%s",
                        SecurityUtil.getBusinessSystem(),
                        Ctx.PREFIX_DATA_PERMISSION_KEY,
                        isWhitelist ? "whiteList" : "blackList",
                        lastStr
                )
        );
    }

    private Map<String, List<String>> getDPInfo(boolean isWhitelist) {
        return getDPInfo(isWhitelist, null, null);
    }

    /**
     * 批量获取数据权限信息
     *
     * @param isWhitelist
     * @param type
     * @param typeIdList
     * @return
     */
    private List<Map<String, List<String>>> getDPInfoList(boolean isWhitelist, String type, List<String> typeIdList) {
        List<String> keys = new ArrayList<>();

        for (String typeId : typeIdList) {
            keys.add(String.format(
                    "%s:%s:%s:%s:%s",
                    SecurityUtil.getBusinessSystem(),
                    Ctx.PREFIX_DATA_PERMISSION_KEY,
                    isWhitelist ? "whiteList" : "blackList",
                    type,
                    typeId
            ));
        }

        return dataScopeHandle.multiGetDPList(keys);
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
