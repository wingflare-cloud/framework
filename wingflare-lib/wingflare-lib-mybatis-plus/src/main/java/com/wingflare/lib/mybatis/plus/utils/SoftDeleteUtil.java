package com.wingflare.lib.mybatis.plus.utils;


import com.wingflare.lib.core.utils.StringUtil;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.constants.DataScope;

/**
 * 数据范围工具类
 *
 * @author shaoyuyao
 * @date 2022/8/18 16:48
 */
public class SoftDeleteUtil {
    /**
     * 根据数据范围标识获取对于的数据范围条件过滤值
     *
     * @param dataScope 数据范围标识
     * @return 数据范围条件过滤值
     */
    public static Integer getValue(String dataScope) {
        if (StringUtil.isEmpty(dataScope) || isNormal(dataScope)) {
            return Constant.LOGIC_NOT_DELETE_VALUE;
        } else if (isDelete(dataScope)) {
            return Constant.LOGIC_DELETE_VALUE;
        }
        return null;
    }

    /**
     * 判断数据范围标识是否为正常
     *
     * @param dataScope 数据范围标识
     * @return
     */
    public static boolean isNormal(String dataScope) {
        return DataScope.NORMAL.equalsIgnoreCase(dataScope);
    }

    /**
     * 判断数据范围标识是否为删除
     *
     * @param dataScope 数据范围标识
     * @return
     */
    public static boolean isDelete(String dataScope) {
        return DataScope.DELETE.equalsIgnoreCase(dataScope);
    }

    /**
     * 判断数据范围标识是否为全部
     *
     * @param dataScope 数据范围标识
     * @return
     */
    public static boolean isAll(String dataScope) {
        return DataScope.ALL.equalsIgnoreCase(dataScope);
    }
}
