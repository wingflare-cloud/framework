package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.OrgDO;
import com.wingflare.business.user.mapper.OrgMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组织机构Server
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:36:56 CST 2023
 */
public class OrgServer extends BaseService<OrgMapper, OrgDO>
{

    /**
    * 获取组织机构Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<BigInteger, OrgDO> getMap(QueryWrapper<OrgDO> wrapper)
	{
        List<OrgDO> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<BigInteger, OrgDO> orgDoMap = new HashMap<>(list.size());

        for (OrgDO orgDo : list) {
            orgDoMap.put(orgDo.getOrgId(), orgDo);
        }

        return orgDoMap;
    }
}
