package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.RolePermissionDO;
import com.wingflare.business.user.mapper.RolePermissionMapper;
import com.wingflare.business.user.wrapper.RolePermissionWrapper;
import com.wingflare.facade.module.user.bo.RolePermissionSearchBO;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统角色权限Server
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:13:02 CST 2023
 */
@Service
public class RolePermissionServer extends BaseService<RolePermissionMapper, RolePermissionDO>
{

    /**
    * 获取系统角色权限Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<BigInteger, RolePermissionDO> getMap(QueryWrapper<RolePermissionDO> wrapper)
	{
        List<RolePermissionDO> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<BigInteger, RolePermissionDO> rolePermissionDoMap = new HashMap<>(list.size());

        for (RolePermissionDO rolePermissionDo : list) {
            rolePermissionDoMap.put(rolePermissionDo.getId(), rolePermissionDo);
        }

        return rolePermissionDoMap;
    }

    /**
     * 批量删除
     * @param bo
     */
    public void batchDelete(RolePermissionSearchBO bo) {
        baseMapper.delete(RolePermissionWrapper.getQueryWrapper(bo));
    }
}
