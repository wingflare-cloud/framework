package com.wingflare.engine.websocket;

/**
 * ws权限代码
 * 两位二进制，第一位表示读第二位表示写
 */
public interface PermissionCode {

    /**
     * 只读
     */
    public static final int READ_ONLY = 0B10;

    /**
     * 只写
     */
    public static final int WRITE_ONLY = 0B01;

    /**
     * 读写
     */
    public static final int WRITE_AND_READ = 0B11;

}
