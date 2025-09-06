package com.wingflare.business.user.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.user.db.JobLevelClassifyDO;
import com.wingflare.business.user.mapper.JobLevelClassifyMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职级分类表 服务实现类
 * </p>
 *
 * @author naizui_ycx
 * @since 2023-04-28
 */
public class JobLevelClassifyServer extends BaseService<JobLevelClassifyMapper, JobLevelClassifyDO> {

    /**
     * 获取jobLevelClassifyMap
     *
     * wrapper 查询参数
     * @return 结果
     */
    public Map<BigInteger, JobLevelClassifyDO> getMap(QueryWrapper<JobLevelClassifyDO> wrapper)
    {
        List<JobLevelClassifyDO> list = list(wrapper);

        if (list == null || list.isEmpty()) {
		    return new HashMap<>();
	    }

        Map<BigInteger, JobLevelClassifyDO> jobLevelClassifyDoMap = new HashMap<>(list.size());

        for (JobLevelClassifyDO jobLevelClassifyDo : list) {
            jobLevelClassifyDoMap.put(jobLevelClassifyDo.getLevelClassifyId(), jobLevelClassifyDo);
        }

        return jobLevelClassifyDoMap;
    }

}