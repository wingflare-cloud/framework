package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.OrgDepartmentDO;
import com.wingflare.business.user.mapper.OrgDepartmentMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机构部门Server
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:40:11 CST 2023
 */
public class OrgDepartmentServer extends BaseService<OrgDepartmentMapper, OrgDepartmentDO>
{

    /**
    * 获取机构部门Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<BigInteger, OrgDepartmentDO> getMap(QueryWrapper<OrgDepartmentDO> wrapper)
	{
        List<OrgDepartmentDO> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<BigInteger, OrgDepartmentDO> orgDepartmentDoMap = new HashMap<>(list.size());

        for (OrgDepartmentDO orgDepartmentDo : list) {
            orgDepartmentDoMap.put(orgDepartmentDo.getDepartmentId(), orgDepartmentDo);
        }

        return orgDepartmentDoMap;
    }
}
