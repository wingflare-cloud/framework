package com.wingflare.lib.security.secret;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.wingflare.lib.security.properties.DataSecretProperties;
import com.wingflare.lib.security.standard.DataSecret;

import jakarta.annotation.Resource;

/**
 * @author naizui_ycx
 * @className DESDataSecret
 * @email chenxi2511@qq.com
 * @date 2023/04/04/18
 * @description RSA数据加密
 */
public class RSADataSecret implements DataSecret {

    @Resource
    private DataSecretProperties dataSecretProperties;

    private RSA instance = null;


    @Override
    public String type() {
        return "RSA";
    }

    @Override
    public String decrypt(String ciphertext) {
        return getInstance().decryptStr(ciphertext, KeyType.PrivateKey);
    }

    @Override
    public String encryption(String plaintext) {
        return getInstance().encryptHex(plaintext, KeyType.PublicKey);
    }

    private RSA getInstance() {
        if (instance == null) {
            synchronized (this) {
                instance = SecureUtil.rsa(dataSecretProperties.getRsaPrivateKeyBase64(),
                        dataSecretProperties.getRsaPublicKeyBase64());
            }
        }

        return instance;
    }

}
