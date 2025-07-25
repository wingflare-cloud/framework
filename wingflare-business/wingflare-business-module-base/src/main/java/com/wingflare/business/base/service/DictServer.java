package com.wingflare.business.base.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.base.db.DictDO;
import com.wingflare.business.base.mapper.DictMapper;
import com.wingflare.lib.mybatis.plus.base.BaseService;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统字典Server
 *
 * @author naizui_ycx
 * @date Sat Mar 04 17:48:17 CST 2023
 */
@Service
public class DictServer extends BaseService<DictMapper, DictDO> {

    /**
     * 获取系统字典Map
     * <p>
     * bo 查询参数
     *
     * @return 结果
     */
    public Map<BigInteger, DictDO> getMap(QueryWrapper<DictDO> wrapper) {
        List<DictDO> list = list(wrapper);

        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<BigInteger, DictDO> dictDoMap = new HashMap<>(list.size());

        for (DictDO dictDo : list) {
            dictDoMap.put(dictDo.getDictId(), dictDo);
        }

        return dictDoMap;
    }
}
