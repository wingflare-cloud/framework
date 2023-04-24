package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.RoleGroupDo;
import com.wingflare.business.user.mapper.RoleGroupMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统角色分组表Server
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:42:56 CST 2023
 */
@Service
public class RoleGroupServer extends BaseService<RoleGroupMapper, RoleGroupDo>
{

    /**
    * 获取系统角色分组表Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<String, RoleGroupDo> getMap(QueryWrapper<RoleGroupDo> wrapper)
	{
        List<RoleGroupDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<String, RoleGroupDo> roleGroupDoMap = new HashMap<>(list.size());

        for (RoleGroupDo roleGroupDo : list) {
            roleGroupDoMap.put(roleGroupDo.getRoleGroupId(), roleGroupDo);
        }

        return roleGroupDoMap;
    }
}
