package com.wingflare.adapter.module.base.nosql.redis.service;

import com.wingflare.abstraction.module.base.DictStorage;
import com.wingflare.facade.module.base.constants.Base;
import com.wingflare.facade.module.base.dto.SimpleDictDto;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

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

    /**
     * redis hash map全量原子性刷新lua脚本
     */
    private final RedisScript<Long> REFRESH_ALL_LIST_LUA_SCRIPT = RedisScript.of(
            "local delRet = redis.call('DEL', KEYS[1])\n"
                    + "if (delRet ~= false) then\n"
                    + "local params = {}\n"
                    + "params[1] = 'LSET'\n"
                    + "params[2] = KEYS[1]\n"
                    + "for i=1, #(ARGV) do\n"
                    + "params[2+i] = ARGV[i] end\n"
                    + "return redis.call(unpack(params)) end\n"
                    + "return 0",
            Long.class
    );

    @Override
    public Long save(String systemCode, SimpleDictDto... simpleDictDtos) {
        return (Long) redisTemplate.execute(
                REFRESH_ALL_LIST_LUA_SCRIPT,
                new ArrayList<String>() {{
                    add(String.format("%s:%s", Base.DICT_CACHE_KEY, systemCode));
                }},
                simpleDictDtos
        );
    }

}
