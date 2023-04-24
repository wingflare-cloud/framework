package com.wingflare.lib.security.standard;

/**
 * 数据解密类
 *
 * @author naizui_ycx
 * @email chenxi2511@qq.com
 */
public interface DataSecret {

    /**
     * 密文类型
     *
     * @return
     */
    String type();

    /**
     * 数据解密
     *
     * @param ciphertext
     * @return
     */
    String decrypt(String ciphertext);

    /**
     * 数据加密
     *
     * @param plaintext
     * @return
     */
    String encryption(String plaintext);

}
