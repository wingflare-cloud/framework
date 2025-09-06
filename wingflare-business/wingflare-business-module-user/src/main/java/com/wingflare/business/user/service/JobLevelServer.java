package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.JobLevelDO;
import com.wingflare.business.user.mapper.JobLevelMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 职级Server
 *
 * @author naizui_ycx
 * @date Fri Mar 10 15:42:34 CST 2023
 */
public class JobLevelServer extends BaseService<JobLevelMapper, JobLevelDO>
{

    /**
    * 获取职级Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<BigInteger, JobLevelDO> getMap(QueryWrapper<JobLevelDO> wrapper)
	{
        List<JobLevelDO> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<BigInteger, JobLevelDO> jobLevelDoMap = new HashMap<>(list.size());

        for (JobLevelDO jobLevelDo : list) {
            jobLevelDoMap.put(jobLevelDo.getJobLevelId(), jobLevelDo);
        }

        return jobLevelDoMap;
    }
}
