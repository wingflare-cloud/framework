package com.wingflare.lib.datascope;

import java.util.List;
import java.util.Map;

/**
 * 数据权限处理器
 *
 * @author naizui_ycx
 * @date 2023/01/14
 */
public interface DataScopeHandle {

    /**
     * 数据权限表达式设置存储KEY
     */
    String PRIORITY_EXPRESSION_KEY = "wf:PRIORITY_EXPRESSION_OPTION";

    /**
     * 获数据取权限表达式
     *
     * @param key
     * @return
     */
    String getCondition(String key);

    /**
     * 获取权限列表
     *
     * @param key
     */
    Map<String, List<String>> getDPList(String key);

    /**
     * 批量获取权限列表
     *
     * @param keys
     */
    List<Map<String, List<String>>> multiGetDPList(List<String> keys);

    /**
     * 获取权限优先级表达式
     *
     * @return
     */
    List<String> getPriorityExpression();

}
