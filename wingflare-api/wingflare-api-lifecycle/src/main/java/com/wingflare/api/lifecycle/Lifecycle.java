package com.wingflare.api.lifecycle;


/**
 * 生命周期接口
 */
public interface Lifecycle {

    /**
     * 启动组件
     */
    void start();

    /**
     * 关闭组件
     */
    void close();

    /**
     * 关闭权重，顺序
     *
     * @return
     */
    default int closeSort() {
        return 0;
    }

    /**
     * 启动权重，顺序
     *
     * @return
     */
    default int startSort() {
        return 0;
    }

}
