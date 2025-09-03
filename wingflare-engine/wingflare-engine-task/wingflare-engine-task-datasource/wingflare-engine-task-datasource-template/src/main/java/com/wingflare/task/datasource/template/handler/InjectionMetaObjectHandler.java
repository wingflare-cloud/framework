package com.wingflare.task.datasource.template.handler;

import cn.hutool.core.util.ObjectUtil;
import com.wingflare.lib.core.enums.HttpStatus;
import com.wingflare.task.datasource.template.exception.TaskDatasourceException;
import com.wingflare.task.datasource.template.persistence.po.CreateDt;
import com.wingflare.task.datasource.template.persistence.po.CreateUpdateDt;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author: dhb52 (adopted from ruoyi-vue-plus)
 * @date: 2024-06-16 23:22
 */
public class InjectionMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof CreateDt baseEntity) {
                LocalDateTime current = ObjectUtil.isNotNull(baseEntity.getCreateDt())
                        ? baseEntity.getCreateDt() : LocalDateTime.now();
                baseEntity.setCreateDt(current);
            }
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof CreateUpdateDt baseEntity) {
                LocalDateTime current = ObjectUtil.isNotNull(baseEntity.getCreateDt())
                        ? baseEntity.getCreateDt() : LocalDateTime.now();
                baseEntity.setUpdateDt(current);
            }
        } catch (Exception e) {
            throw new TaskDatasourceException(" Automatic injection exception =>" + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof CreateUpdateDt baseEntity) {
                LocalDateTime current = LocalDateTime.now();
                // 更新时间填充(不管为不为空)
                baseEntity.setUpdateDt(current);
            }
        } catch (Exception e) {
            throw new TaskDatasourceException(" Automatic injection exception =>" + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
