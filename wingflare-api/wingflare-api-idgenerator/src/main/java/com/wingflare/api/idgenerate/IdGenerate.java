package com.wingflare.api.idgenerate;

/**
 * Id生成器接口
 */
public interface IdGenerate {

    long nextId();

    long nextId(String key);

}
