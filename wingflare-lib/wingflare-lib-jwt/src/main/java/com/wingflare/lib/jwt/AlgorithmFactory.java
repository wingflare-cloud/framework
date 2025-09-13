package com.wingflare.lib.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.wingflare.lib.config.ConfigUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


public class AlgorithmFactory {

    private AlgorithmFactory() {}

    public static AlgorithmFactory getInstance() {
        return Singleton.instance;
    }

    public Algorithm createAlgorithm() {
        String algorithm = ConfigUtil.getProperty("jwt.algorithm", "HMAC256");

        try {
            switch (algorithm) {
                case "HMAC256":
                    return Algorithm.HMAC256(ConfigUtil.getProperty("jwt.secretKey"));
                case "HMAC512":
                    return Algorithm.HMAC512(ConfigUtil.getProperty("jwt.secretKey"));
                case "RSA256":
                    return Algorithm.RSA256((RSAPublicKey) loadPublicKey(ConfigUtil.getProperty("jwt.publicKeyPath")),
                            (RSAPrivateKey) loadPrivateKey(ConfigUtil.getProperty("jwt.privateKeyPath")));
                case "RSA512":
                    return Algorithm.RSA512((RSAPublicKey) loadPublicKey(ConfigUtil.getProperty("jwt.publicKeyPath")),
                            (RSAPrivateKey) loadPrivateKey(ConfigUtil.getProperty("jwt.privateKeyPath")));
                default:
                    throw new IllegalArgumentException("不支持的加密算法: " + algorithm);
            }
        } catch (Exception e) {
            throw new RuntimeException("创建加密算法实例失败: " + e.getMessage(), e);
        }
    }

    private PublicKey loadPublicKey(String filePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] keyBytes = new byte[fis.available()];
            fis.read(keyBytes);

            // 移除可能存在的PEM格式头和尾
            String publicKeyPEM = new String(keyBytes)
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(publicKeyPEM);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(new X509EncodedKeySpec(decoded));
        }
    }

    private PrivateKey loadPrivateKey(String filePath) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] keyBytes = new byte[fis.available()];
            fis.read(keyBytes);

            // 移除可能存在的PEM格式头和尾
            String privateKeyPEM = new String(keyBytes)
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s", "");

            byte[] decoded = Base64.getDecoder().decode(privateKeyPEM);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decoded));
        }
    }

    private static class Singleton {
        private static final AlgorithmFactory instance = new AlgorithmFactory();
    }

}