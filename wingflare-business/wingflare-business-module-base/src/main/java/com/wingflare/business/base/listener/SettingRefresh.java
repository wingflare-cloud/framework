package com.wingflare.business.base.listener;

import com.wingflare.business.base.constants.BaseEventName;
import com.wingflare.facade.module.base.constants.Base;
import com.wingflare.facade.module.base.dto.SettingDto;
import com.wingflare.lib.spring.event.Event;
import com.wingflare.lib.standard.CacheService;
import com.wingflare.lib.standard.enums.OnOffEnum;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * @ClassName SettingRefresh
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 刷新系统设置
 */
@Component
public class SettingRefresh {

    @Resource
    private CacheService cacheService;


    @EventListener(condition = "#event.eventName eq '" + BaseEventName.SETTING_CREATED + "'")
    public void createHandle(Event event) {
        saveCache((SettingDto) event.getData());
    }

    @EventListener(condition = "#event.eventName eq '" + BaseEventName.SETTING_UPDATED + "'")
    public void updateHandle(Event event) {
        saveCache((SettingDto) event.getData());
    }

    @EventListener(condition = "#event.eventName eq '" + BaseEventName.SETTING_DELETED + "'")
    public void deleteHandle(Event event) {
        SettingDto settingDto = (SettingDto) event.getData();
        cacheService.delCacheMapValue(Base.SETTING_CACHE_KEY, settingDto.getSettingCode());
    }

    private void saveCache(SettingDto settingDto) {
        if (OnOffEnum.ON.getValue().equals(settingDto.getState())) {
            cacheService.setCacheMapValue(
                    Base.SETTING_CACHE_KEY,
                    settingDto.getSettingCode(),
                    settingDto.getSettingValue()
            );
        } else {
            cacheService.delCacheMapValue(Base.SETTING_CACHE_KEY, settingDto.getSettingCode());
        }
    }

}
