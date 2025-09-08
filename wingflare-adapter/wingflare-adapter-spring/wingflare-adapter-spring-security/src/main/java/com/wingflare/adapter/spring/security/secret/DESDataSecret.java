package com.wingflare.adapter.spring.security.secret;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import com.wingflare.adapter.spring.security.properties.DataSecretProperties;
import com.wingflare.api.security.DataSecret;

import java.nio.charset.StandardCharsets;

/**
 * @author naizui_ycx
 * @className DESDataSecret
 * @email chenxi2511@qq.com
 * @date 2023/04/04/18
 * @description DES数据加密
 */
public class DESDataSecret implements DataSecret {

    private final DataSecretProperties dataSecretProperties;

    private DES instance = null;

    public DESDataSecret(DataSecretProperties dataSecretProperties) {
        this.dataSecretProperties = dataSecretProperties;
    }

    @Override
    public String type() {
        return "DES";
    }

    @Override
    public String decrypt(String ciphertext) {
        return getInstance().decryptStr(ciphertext);
    }

    @Override
    public String encryption(String plaintext) {
        return getInstance().encryptHex(plaintext);
    }

    private DES getInstance() {
        if (instance == null) {
            synchronized (this) {
                instance = SecureUtil.des(dataSecretProperties.getDesKey().getBytes(StandardCharsets.UTF_8));
            }
        }

        return instance;
    }

}
