package com.wingflare.lib.mybatis.plus.generator;


import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.wingflare.lib.spring.utils.SnowflakeUtil;

import jakarta.annotation.Resource;

/**
 * 自定义主键生成器
 *
 * @author shaoyuyao
 * @date 2022/8/16 13:35
 */
public class CustomIdGenerator implements IdentifierGenerator {

    @Resource
    private SnowflakeUtil snowflakeUtil;

    @Override
    public Number nextId(Object entity) {
        return snowflakeUtil.nextLongId();
    }

}
