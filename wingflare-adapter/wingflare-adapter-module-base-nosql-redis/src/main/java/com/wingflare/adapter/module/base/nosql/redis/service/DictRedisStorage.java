package com.wingflare.adapter.module.base.nosql.redis.service;


import com.wingflare.abstraction.module.base.DictStorage;
import com.wingflare.facade.module.base.constants.Base;
import com.wingflare.facade.module.base.dto.SimpleDictDTO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @ClassName DictRedisStore
 * @Author naizui_ycx
 * @Date 2023/03/04
 * @Description redis字典存储驱动
 */
@Component
public class DictRedisStorage implements DictStorage {

    @SuppressWarnings("rawtypes" )
    private final RedisTemplate redisTemplate;

    DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>("local key = KEYS[1]\n" +
            "local args = ARGV\n" +
            "local total = #args\n" +
            "local batchSize = 1000\n" +
            "for i = 1, total, batchSize do\n" +
            "    local endIdx = math.min(i + batchSize - 1, total)\n" +
            "    local batch = {}\n" +
            "    for j = i, endIdx do\n" +
            "        table.insert(batch, args[j])\n" +
            "    end\n" +
            "    redis.call('RPUSH', key, unpack(batch))\n" +
            "end\n" +
            "return total", Long.class);

    public DictRedisStorage(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long save(SimpleDictDTO... simpleDictDTOS) {
        redisTemplate.delete(Base.DICT_CACHE_KEY);
        return (Long) redisTemplate.execute(redisScript, Collections.singletonList(Base.DICT_CACHE_KEY), simpleDictDTOS);
    }

}
