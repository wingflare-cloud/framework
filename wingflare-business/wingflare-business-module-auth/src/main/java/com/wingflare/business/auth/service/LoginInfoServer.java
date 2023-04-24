package com.wingflare.business.auth.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.auth.db.LoginInfoDo;
import com.wingflare.business.auth.mapper.LoginInfoMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登陆信息Server
 *
 * @author naizui_ycx
 * @date Fri Mar 10 14:29:43 CST 2023
 */
@Service
public class LoginInfoServer extends BaseService<LoginInfoMapper, LoginInfoDo> {

    /**
     * 获取登陆信息Map
     * <p>
     * bo 查询参数
     *
     * @return 结果
     */
    public Map<String, LoginInfoDo> getMap(QueryWrapper<LoginInfoDo> wrapper) {
        List<LoginInfoDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<String, LoginInfoDo> loginInfoDoMap = new HashMap<>(list.size());

        for (LoginInfoDo loginInfoDo : list) {
            loginInfoDoMap.put(loginInfoDo.getLoginId(), loginInfoDo);
        }

        return loginInfoDoMap;
    }
}
