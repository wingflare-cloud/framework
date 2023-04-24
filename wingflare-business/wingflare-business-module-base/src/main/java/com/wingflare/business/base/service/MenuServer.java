package com.wingflare.business.base.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.base.db.MenuDo;
import com.wingflare.business.base.mapper.MenuMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单Server
 *
 * @author naizui_ycx
 * @date Sat Mar 04 21:30:08 CST 2023
 */
@Service
public class MenuServer extends BaseService<MenuMapper, MenuDo> {

    /**
     * 获取系统菜单Map
     * <p>
     * bo 查询参数
     *
     * @return 结果
     */
    public Map<String, MenuDo> getMap(QueryWrapper<MenuDo> wrapper) {
        List<MenuDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<String, MenuDo> menuDoMap = new HashMap<>(list.size());

        for (MenuDo menuDo : list) {
            menuDoMap.put(menuDo.getMenuId(), menuDo);
        }

        return menuDoMap;
    }
}
