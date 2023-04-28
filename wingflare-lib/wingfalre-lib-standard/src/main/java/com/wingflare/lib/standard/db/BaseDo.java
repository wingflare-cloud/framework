package com.wingflare.lib.standard.db;

import java.io.Serializable;
import java.util.Set;

/**
 * @author naizui_ycx
 * @date {2021/12/18}
 * @description 基础实体类
 */
public interface BaseDo extends Serializable {

    /**
     *
     * 设置主键
     *
     * @param pk
     *
     * @return
     */
    public void setPk(String pk);

    /**
     * 获取主键
     *
     * @return
     */
    public String getPk();

    /**
     * 清除需要更新字段中的null字段
     *
     * @return
     */
    public void clearNullNewField();

    /**
     * 添加一个需要更新的字段
     *
     * @param field 字段名
     * @return
     */
    public void addNewField(String field);

    /**
     * 删除一个需要更新的字段
     *
     * @param field 字段名
     * @return
     */
    public void removeNewField(String field);

    /**
     * 判断当前实体是否存在需要更新的字段
     *
     * @return
     */
    public boolean hasNewField();

    /**
     * 判断实体中某个字段是否需要更新
     *
     * @param field
     *
     * @return
     */
    public boolean isNewField(String field);

    /**
     * 获取全部需要更新的字段的字段名
     *
     * @return
     */
    public Set<String> getNewField();

    /**
     * 清除所有需要需要更新的字段
     *
     * @return
     */
    public void clearNewField();

}
