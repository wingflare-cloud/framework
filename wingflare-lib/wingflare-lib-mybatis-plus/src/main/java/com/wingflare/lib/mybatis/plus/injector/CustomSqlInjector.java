package com.wingflare.lib.mybatis.plus.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.wingflare.lib.mybatis.plus.injector.method.delete.Delete;
import com.wingflare.lib.mybatis.plus.injector.method.delete.DeleteBatchByIds;
import com.wingflare.lib.mybatis.plus.injector.method.delete.DeleteById;
import com.wingflare.lib.mybatis.plus.injector.method.delete.DeleteByMap;
import com.wingflare.lib.mybatis.plus.injector.method.delete.ForceDeleteBatchByIds;
import com.wingflare.lib.mybatis.plus.injector.method.delete.ForceDeleteById;
import com.wingflare.lib.mybatis.plus.injector.method.insert.Insert;
import com.wingflare.lib.mybatis.plus.injector.method.select.join.SelectJoinList;
import com.wingflare.lib.mybatis.plus.injector.method.select.join.SelectJoinPage;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectBatchByIds;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectById;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectByMap;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectCount;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectExist;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectExistById;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectList;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectMaps;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectMapsPage;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectObjs;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectOne;
import com.wingflare.lib.mybatis.plus.injector.method.select.single.SelectPage;
import com.wingflare.lib.mybatis.plus.injector.method.update.Update;
import com.wingflare.lib.mybatis.plus.injector.method.update.UpdateById;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义SQL注入器，注入自定义通用方法
 *
 * @author shaoyuyao
 * @date 2022/8/10 16:35
 */
public class CustomSqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        List<AbstractMethod> methodList = new ArrayList<>();

        // 插入
        methodList.add(new Insert());

        // 删除
        methodList.add(new Delete());
        methodList.add(new DeleteByMap());

        // 修改
        methodList.add(new Update());

        // 查询
        methodList.add(new SelectByMap());
        methodList.add(new SelectCount());
        methodList.add(new SelectMaps());
        methodList.add(new SelectMapsPage());
        methodList.add(new SelectObjs());
        methodList.add(new SelectOne());
        methodList.add(new SelectList());
        methodList.add(new SelectPage());
        methodList.add(new SelectExist());
        methodList.add(new SelectJoinList());
        methodList.add(new SelectJoinPage());

        // 实体类包含主键
        if (tableInfo.havePK()) {
            // 删除
            methodList.add(new DeleteById());
            methodList.add(new DeleteBatchByIds());
            methodList.add(new ForceDeleteById());
            methodList.add(new ForceDeleteBatchByIds());

            // 修改
            methodList.add(new UpdateById());

            // 查询
            methodList.add(new SelectById());
            methodList.add(new SelectBatchByIds());
            methodList.add(new SelectExistById());
        }

        return methodList;
    }
}
