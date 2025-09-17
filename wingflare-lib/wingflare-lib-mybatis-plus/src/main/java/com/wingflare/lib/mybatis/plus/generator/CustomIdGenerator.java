package com.wingflare.lib.mybatis.plus.generator;


import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

import com.wingflare.api.idgenerate.IdGenerate;

/**
 * 自定义主键生成器
 *
 * @author shaoyuyao
 * @date 2022/8/16 13:35
 */
public class CustomIdGenerator implements IdentifierGenerator {

    private final IdGenerate idGenerate;

    public CustomIdGenerator(IdGenerate idGenerate) {
        this.idGenerate = idGenerate;
    }

    @Override
    public Number nextId(Object entity) {
        return idGenerate.nextId();
    }

}
