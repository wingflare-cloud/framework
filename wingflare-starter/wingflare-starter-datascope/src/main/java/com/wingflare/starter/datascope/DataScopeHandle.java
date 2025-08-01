package com.wingflare.starter.datascope;


import com.wingflare.lib.standard.CacheService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DataScopeHandle implements com.wingflare.lib.datascope.DataScopeHandle {

    @Resource
    private CacheService cacheService;

    @Override
    public String getCondition(String key) {
        return cacheService.getCacheObject(key);
    }

    @Override
    public Map<String, List<String>> getDPList(String key) {
        return cacheService.getCacheMap(key);
    }

    @Override
    public List<Map<String, List<String>>> multiGetDPList(List<String> keys) {
        return cacheService.getCacheListObject(keys);
    }

    @Override
    public List<String> getPriorityExpression() {
        return cacheService.getCacheList(com.wingflare.lib.datascope.DataScopeHandle.PRIORITY_EXPRESSION_KEY);
    }

}
