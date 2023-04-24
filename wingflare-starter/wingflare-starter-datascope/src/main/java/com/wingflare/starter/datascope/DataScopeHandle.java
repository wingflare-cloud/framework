package com.wingflare.starter.datascope;


import com.wingflare.lib.standard.CacheService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
    public Map<String, List<String>> getBlacklist(String key) {
        return cacheService.getCacheMap(key);
    }

    @Override
    public Map<String, List<String>> getWhitelist(String key) {
        return cacheService.getCacheMap(key);
    }

}
