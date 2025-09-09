package com.wingflare.business.base.listener;


import com.wingflare.api.core.enums.OnOffEnum;
import com.wingflare.facade.module.base.constants.Base;
import com.wingflare.facade.module.base.dto.SettingDTO;
import com.wingflare.facade.module.base.event.SettingCreatedEvent;
import com.wingflare.facade.module.base.event.SettingDeletedEvent;
import com.wingflare.facade.module.base.event.SettingUpdatedEvent;
import com.wingflare.lib.standard.CacheService;
import org.springframework.context.event.EventListener;


/**
 * @ClassName SettingRefresh
 * @Author naizui_ycx
 * @Date 2023/03/03 03
 * @Description 刷新系统设置
 */
public class SettingRefresh {

    private final CacheService cacheService;

    public SettingRefresh(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @EventListener({SettingCreatedEvent.class})
    public void createHandle(SettingCreatedEvent event) {
        saveCache(event.getSource());
    }

    @EventListener({SettingUpdatedEvent.class})
    public void updateHandle(SettingUpdatedEvent event) {
        saveCache(event.getSource());
    }

    @EventListener({SettingDeletedEvent.class})
    public void deleteHandle(SettingDeletedEvent event) {
        cacheService.delCacheMapValue(Base.SETTING_CACHE_KEY, event.getSource().getSettingCode());
    }

    private void saveCache(SettingDTO settingDto) {
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
