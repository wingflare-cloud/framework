package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.JobLevelDo;
import com.wingflare.business.user.mapper.JobLevelMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

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
@Service
public class JobLevelServer extends BaseService<JobLevelMapper, JobLevelDo>
{

    /**
    * 获取职级Map
    *
    * bo 查询参数
    * @return 结果
    */
    public Map<BigInteger, JobLevelDo> getMap(QueryWrapper<JobLevelDo> wrapper)
	{
        List<JobLevelDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
			return new HashMap<>();
        }

        Map<BigInteger, JobLevelDo> jobLevelDoMap = new HashMap<>(list.size());

        for (JobLevelDo jobLevelDo : list) {
            jobLevelDoMap.put(jobLevelDo.getJobLevelId(), jobLevelDo);
        }

        return jobLevelDoMap;
    }
}
