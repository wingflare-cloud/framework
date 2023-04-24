package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import com.wingflare.business.user.db.IdentityDo;
import com.wingflare.business.user.mapper.IdentityMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 岗位身份Server
 *
 * @author naizui_ycx
 * @date Sun Apr 02 10:15:03 CST 2023
 */
@Service
public class IdentityServer extends BaseService<IdentityMapper, IdentityDo>
{

    /**
    * 获取岗位身份Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<String, IdentityDo> getMap(QueryWrapper<IdentityDo> wrapper)
	{
        List<IdentityDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<String, IdentityDo> identityDoMap = new HashMap<>(list.size());

        for (IdentityDo identityDo : list) {
            identityDoMap.put(identityDo.getIdentityId(), identityDo);
        }

        return identityDoMap;
    }

}
