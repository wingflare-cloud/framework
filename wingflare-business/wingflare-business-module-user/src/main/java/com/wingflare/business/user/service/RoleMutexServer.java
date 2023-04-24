package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.RoleMutexDo;
import com.wingflare.business.user.mapper.RoleMutexMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统角色互斥关系Server
 *
 * @author naizui_ycx
 * @date Thu Mar 09 10:09:10 CST 2023
 */
@Service
public class RoleMutexServer extends BaseService<RoleMutexMapper, RoleMutexDo>
{

    /**
    * 获取系统角色互斥关系Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<String, RoleMutexDo> getMap(QueryWrapper<RoleMutexDo> wrapper)
	{
        List<RoleMutexDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<String, RoleMutexDo> roleMutexDoMap = new HashMap<>(list.size());

        for (RoleMutexDo roleMutexDo : list) {
            roleMutexDoMap.put(roleMutexDo.getId(), roleMutexDo);
        }

        return roleMutexDoMap;
    }
}
