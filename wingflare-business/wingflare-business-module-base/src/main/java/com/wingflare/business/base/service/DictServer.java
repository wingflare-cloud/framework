package com.wingflare.business.base.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wingflare.business.base.db.DictDo;
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
public class DictServer extends BaseService<DictMapper, DictDo> {

    /**
     * 获取系统字典Map
     * <p>
     * bo 查询参数
     *
     * @return 结果
     */
    public Map<BigInteger, DictDo> getMap(QueryWrapper<DictDo> wrapper) {
        List<DictDo> list = list(wrapper);

        if (list == null || list.isEmpty()) {
            return new HashMap<>();
        }

        Map<BigInteger, DictDo> dictDoMap = new HashMap<>(list.size());

        for (DictDo dictDo : list) {
            dictDoMap.put(dictDo.getDictId(), dictDo);
        }

        return dictDoMap;
    }
}
