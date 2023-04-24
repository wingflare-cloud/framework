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
     * 获数据取权限表达式
     *
     * @param key
     * @return
     */
    public String getCondition(String key);

    /**
     * 获取黑名单权限
     *
     * @param key
     */
    Map<String, List<String>> getBlacklist(String key);

    /**
     * 获取白名单权限
     *
     * @param key
     */
    Map<String, List<String>> getWhitelist(String key);

}
