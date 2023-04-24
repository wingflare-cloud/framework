package com.wingflare.lib.mybatis.plus.enums.fucntion;

/**
 * 数据库函数
 *
 * @author shaoyuyao
 * @date 2022/8/23 17:59
 */
public interface DbFunction {
    /**
     * 获取SQL，例如 SUM(%s)
     *
     * @return
     */
    String getSql();
}
