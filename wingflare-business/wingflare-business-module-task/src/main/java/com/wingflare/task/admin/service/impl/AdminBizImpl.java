package com.wingflare.task.admin.service.impl;


import com.wingflare.lib.task.biz.AdminBiz;
import com.wingflare.lib.task.biz.model.HandleCallbackParam;
import com.wingflare.lib.task.biz.model.RegistryParam;
import com.wingflare.lib.task.biz.model.ReturnT;
import com.wingflare.task.admin.core.thread.JobCompleteHelper;
import com.wingflare.task.admin.core.thread.JobRegistryHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuxueli 2017-07-27 21:54:20
 */
@Service
public class AdminBizImpl implements AdminBiz {


    @Override
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        return JobCompleteHelper.getInstance().callback(callbackParamList);
    }

    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        return JobRegistryHelper.getInstance().registry(registryParam);
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        return JobRegistryHelper.getInstance().registryRemove(registryParam);
    }

}
