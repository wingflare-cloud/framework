package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.OrgDepartmentDo;
import com.wingflare.business.user.mapper.OrgDepartmentMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

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
@Service
public class OrgDepartmentServer extends BaseService<OrgDepartmentMapper, OrgDepartmentDo>
{

    /**
    * 获取机构部门Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<BigInteger, OrgDepartmentDo> getMap(QueryWrapper<OrgDepartmentDo> wrapper)
	{
        List<OrgDepartmentDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<BigInteger, OrgDepartmentDo> orgDepartmentDoMap = new HashMap<>(list.size());

        for (OrgDepartmentDo orgDepartmentDo : list) {
            orgDepartmentDoMap.put(orgDepartmentDo.getDepartmentId(), orgDepartmentDo);
        }

        return orgDepartmentDoMap;
    }
}
