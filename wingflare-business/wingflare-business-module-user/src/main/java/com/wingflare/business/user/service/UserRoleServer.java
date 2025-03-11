package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.UserRoleDo;
import com.wingflare.business.user.mapper.UserRoleMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户角色表 服务实现类
 * </p>
 *
 * @author naizui_ycx
 * @since 2025-03-11
 */
@Service
public class UserRoleServer extends BaseService<UserRoleMapper, UserRoleDo> {

    /**
     * 获取userRoleMap
     *
     * wrapper 查询参数
     * @return 结果
     */
    public Map<String, UserRoleDo> getMap(QueryWrapper<UserRoleDo> wrapper)
    {
        List<UserRoleDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
		    return new HashMap<>();
	    }

        Map<String, UserRoleDo> userRoleDoMap = new HashMap<>(list.size());

        for (UserRoleDo userRoleDo : list) {
            userRoleDoMap.put(userRoleDo.getId(), userRoleDo);
        }

        return userRoleDoMap;
    }

}