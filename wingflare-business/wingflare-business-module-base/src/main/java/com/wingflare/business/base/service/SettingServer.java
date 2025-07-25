package com.wingflare.business.base.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.base.db.SettingDO;
import com.wingflare.business.base.mapper.SettingMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统设置Server
 *
 * @author naizui_ycx
 * @date Fri Mar 03 09:48:21 CST 2023
 */
@Service
public class SettingServer extends BaseService<SettingMapper, SettingDO> {

    /**
     * 获取系统设置Map
     * <p>
     * bo 查询参数
     *
     * @return 结果
     */
    public Map<BigInteger, SettingDO> getMap(QueryWrapper<SettingDO> wrapper) {
        List<SettingDO> list = list(wrapper);

        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<BigInteger, SettingDO> settingDoMap = new HashMap<>(list.size());

        for (SettingDO settingDo : list) {
            settingDoMap.put(settingDo.getSettingId(), settingDo);
        }

        return settingDoMap;
    }
}
