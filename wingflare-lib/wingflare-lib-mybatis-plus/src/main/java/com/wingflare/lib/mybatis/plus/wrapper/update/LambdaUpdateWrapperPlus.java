package com.wingflare.lib.mybatis.plus.wrapper.update;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.wingflare.lib.mybatis.plus.constants.Constant;

/**
 * Lambda 更新封装，在 MyBatis-Plus 的 LambdaUpdateWrapper 基础上进行扩展
 *
 * @author shaoyuyao
 * @date 2022/8/27 9:23
 */
public class LambdaUpdateWrapperPlus<T> extends LambdaUpdateWrapper<T> {
    /**
     * 设置要进行自增的列，例如 SET money = money + 1
     *
     * @param column
     * @return
     */
    public LambdaUpdateWrapper<T> setInc(SFunction<T, ?> column) {
        return setInc(column, 1);
    }

    /**
     * 设置要进行自增的列，例如 SET money = money + val
     *
     * @param column
     * @param val    负数就表示要进行自减
     * @return
     */
    public LambdaUpdateWrapper<T> setInc(SFunction<T, ?> column, Number val) {
        if (val == null) {
            val = 1;
        }

        if (val.intValue() < 0) {
            return setSub(column, -val.intValue());
        }

        String columnName = columnToString(column);
        return super.setSql(columnName + Constant.EQUALS_AROUND_SPACE + columnName + Constant.PLUS_AROUND_SPACE + val);
    }

    /**
     * 设置要进行自减的列，例如 SET money = money - 1
     *
     * @param column
     * @return
     */
    public LambdaUpdateWrapper<T> setSub(SFunction<T, ?> column) {
        return setSub(column, 1);
    }

    /**
     * 设置要进行自减的列，例如 SET money = money - 1
     *
     * @param column
     * @param val    负数就表示要进行自增
     * @return
     */
    public LambdaUpdateWrapper<T> setSub(SFunction<T, ?> column, Number val) {
        if (val == null) {
            val = 1;
        }

        if (val.intValue() < 0) {
            return setInc(column, -val.intValue());
        }

        String columnName = columnToString(column);
        return super.setSql(columnName + Constant.EQUALS_AROUND_SPACE + columnName + Constant.SUB_AROUND_SPACE + val);
    }
}
