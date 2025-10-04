package com.wingflare.api.retry;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

/**
 * 默认的idempotentId 生成器
 *
 * @author: opensnail
 * @date : 2022-03-08 09:42
 */
public class SimpleIdempotentIdGenerate implements IdempotentIdGenerate {

    @Override
    public String idGenerate(IdempotentIdContext context) throws Exception {
        if (Objects.isNull(context)) {
            return UUID.randomUUID().toString();
        }

        String id = MessageFormat.format("{0}_{1}_{2}_{3}", context.getScene(), context.getTargetClassName(),
                context.getMethodName(), context.getSerializeArgs());
        return md5(id.getBytes(StandardCharsets.UTF_8));
    }

    public String md5(byte[] inputBytes) {
        try {
            // 获取 MD5 算法的 MessageDigest 实例
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算哈希值（字节数组）
            byte[] digestBytes = md.digest(inputBytes);
            // 将字节数组转为 32 位十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : digestBytes) {
                // 字节转十六进制（确保两位，不足补 0）
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // MD5 是标准算法，所有 JDK 都支持，此异常理论上不会抛出
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

}
