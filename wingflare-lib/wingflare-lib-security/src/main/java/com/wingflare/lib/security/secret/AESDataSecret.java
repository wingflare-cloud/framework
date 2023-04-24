package com.wingflare.lib.security.secret;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.wingflare.lib.security.properties.DataSecretProperties;
import com.wingflare.lib.security.standard.DataSecret;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * @author naizui_ycx
 * @className AESDataSecret
 * @email chenxi2511@qqq.com
 * @date 2023/04/04/18
 * @description AES数据加密
 */
public class AESDataSecret implements DataSecret {

    @Resource
    private DataSecretProperties dataSecretProperties;

    private AES instance;

    @Override
    public String type() {
        return "AES";
    }

    @Override
    public String decrypt(String ciphertext) {
        return getInstance().decryptStr(ciphertext);
    }

    @Override
    public String encryption(String plaintext) {
        return getInstance().encryptHex(plaintext);
    }

    private AES getInstance() {
        if (instance == null) {
            synchronized (this) {
                instance = SecureUtil.aes(dataSecretProperties.getAesKey().getBytes(StandardCharsets.UTF_8));
            }
        }

        return instance;
    }

}
