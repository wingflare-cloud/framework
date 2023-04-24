package com.wingflare.lib.mybatis.plus.base;


import com.baomidou.mybatisplus.annotation.TableField;
import com.wingflare.lib.standard.db.BaseDo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author naizui_ycx
 * @date {2022/3/1}
 * @description
 */
public abstract class BaseDoAbstract implements BaseDo {

    @TableField(exist = false)
    protected Set<String> newField = new HashSet<>();

    @Override
    public BaseDoAbstract addNewField(String field) {
        newField.add(field);
        return this;
    }

    @Override
    public BaseDoAbstract removeNewField(String field) {
        newField.remove(field);
        return this;
    }

    @Override
    public boolean hasNewField() {
        return !newField.isEmpty();
    }

    @Override
    public boolean isNewField(String field) {
        return newField.contains(field);
    }

    @Override
    public Set<String> getNewField() {
        return newField;
    }

    @Override
    public BaseDoAbstract clearNewField() {
        newField.clear();
        return this;
    }

}
