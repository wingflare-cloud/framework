package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.RoleDO;
import com.wingflare.business.user.mapper.RoleMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统角色Server
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:04:01 CST 2023
 */
@Service
public class RoleServer extends BaseService<RoleMapper, RoleDO>
{

    /**
    * 获取系统角色Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<BigInteger, RoleDO> getMap(QueryWrapper<RoleDO> wrapper)
	{
        List<RoleDO> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<BigInteger, RoleDO> roleDoMap = new HashMap<>(list.size());

        for (RoleDO roleDo : list) {
            roleDoMap.put(roleDo.getRoleId(), roleDo);
        }

        return roleDoMap;
    }
}
