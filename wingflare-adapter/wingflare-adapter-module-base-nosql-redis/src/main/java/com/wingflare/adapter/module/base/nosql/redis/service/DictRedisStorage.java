package com.wingflare.adapter.module.base.nosql.redis.service;

import com.wingflare.abstraction.module.base.DictStorage;
import com.wingflare.facade.module.base.constants.Base;
import com.wingflare.facade.module.base.dto.SimpleDictDto;
import com.wingflare.lib.core.utils.CollectionUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName DictRedisStore
 * @Author naizui_ycx
 * @Date 2023/03/04
 * @Description redis字典存储驱动
 */
@Component
public class DictRedisStorage implements DictStorage {

    @SuppressWarnings("rawtypes" )
    @Resource
    private RedisTemplate redisTemplate;

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

    @Override
    @Transactional
    public Long save(String systemCode, SimpleDictDto... simpleDictDtos) {
        String key = String.format("%s:%s", Base.DICT_CACHE_KEY, systemCode);
        Boolean isDeleted = redisTemplate.delete(key);

        if (!isDeleted) {
            throw new RuntimeException("Failed to delete key: " + key);
        }

        List<Collection<SimpleDictDto>> lists = CollectionUtil.splitCollection(Arrays.asList(simpleDictDtos), 3000);
        Long num = 0L;

        for (Collection<SimpleDictDto> list : lists) {
            num += (Long) redisTemplate.execute(redisScript, Collections.singletonList(key), list);
        }

        return num;
    }

}
