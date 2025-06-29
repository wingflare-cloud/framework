package com.wingflare.lib.mybatis.plus.plugin.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wingflare.lib.standard.utils.SecurityUtil;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import org.apache.ibatis.reflection.MetaObject;

import java.math.BigInteger;
import java.util.Date;

/**
 * 自定义元对象处理器，实现数据自动填充功能
 *
 * @author shaoyuyao
 * @date 2022/8/16 13:57
 */
public class CustomMetaObjectHandler implements MetaObjectHandler {
    /**
     * 新增操作填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        BigInteger userId = SecurityUtil.getUserId();
        String username = SecurityUtil.getUsername();
        Date date = new Date();
        this.strictInsertFill(metaObject, Constant.CREATED_TIME_FIELD, Date.class, date);
        this.strictInsertFill(metaObject, Constant.UPDATED_TIME_FIELD, Date.class, date);
        this.strictInsertFill(metaObject, Constant.CREATE_USER_FIELD, String.class, username);
        this.strictInsertFill(metaObject, Constant.CREATE_USER_ID_FIELD, BigInteger.class, userId);
        this.strictInsertFill(metaObject, Constant.UPDATE_USER_FIELD, String.class, username);
        this.strictInsertFill(metaObject, Constant.UPDATE_USER_ID_FIELD, BigInteger.class, userId);
        this.strictInsertFill(metaObject, Constant.IS_DELETE_FIELD, Integer.class, Constant.LOGIC_NOT_DELETE_VALUE);
        this.strictInsertFill(metaObject, Constant.VERSION_FIELD, Integer.class, 0);
    }

    /**
     * 更新操作填充
     *
     * @param metaObject 元对象
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 默认开启强制填充
        boolean isAutoUpdateTime = true;
        boolean isAutoUpdateInfo = true;

        if (metaObject.hasGetter(Constant.AUTO_UPDATE_TIME_FIELD)) {
            Object autoUpdateTime = metaObject.getValue(Constant.AUTO_UPDATE_TIME_FIELD);
            if (autoUpdateTime instanceof Boolean) {
                isAutoUpdateTime = (boolean) autoUpdateTime;
            }
        }
        if (metaObject.hasGetter(Constant.AUTO_UPDATE_INFO_FIELD)) {
            Object autoUpdateInfo = metaObject.getValue(Constant.AUTO_UPDATE_INFO_FIELD);
            if (autoUpdateInfo instanceof Boolean) {
                isAutoUpdateInfo = (boolean) autoUpdateInfo;
            }
        }

        if (isAutoUpdateTime) {
            // 强制填充
            metaObject.setValue(Constant.UPDATED_TIME_FIELD, null);
            this.strictUpdateFill(metaObject, Constant.UPDATED_TIME_FIELD, Date.class, new Date());
        }

        if (isAutoUpdateInfo) {
            // 强制填充
            metaObject.setValue(Constant.UPDATE_USER_FIELD, null);
            metaObject.setValue(Constant.UPDATE_USER_ID_FIELD, null);
            this.strictUpdateFill(metaObject, Constant.UPDATE_USER_FIELD, String.class, SecurityUtil.getUsername());
            this.strictUpdateFill(metaObject, Constant.UPDATE_USER_ID_FIELD, BigInteger.class, SecurityUtil.getUserId());
        }
    }
}
