package com.wingflare.lib.mybatis.plus.injector.method.select.single;

import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.wingflare.lib.mybatis.plus.enums.CustomMethod;
import com.wingflare.lib.mybatis.plus.injector.CustomAbstractMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据Wrapper条件，判断数据是否存在
 *
 * @author shaoyuyao
 * @date 2022/8/10 15:54
 */
public class SelectExist extends CustomAbstractMethod {

    public SelectExist() {
        super(CustomMethod.SELECT_EXIST.getMethod());
    }

    public SelectExist(String name) {
        super(name);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        CustomMethod customMethod = CustomMethod.SELECT_EXIST;

        String sql = String.format(
                customMethod.getSql(),
                tableInfo.getTableName(),
                sqlWhereEntityWrapper(true, tableInfo)
        );

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return this.addSelectMappedStatementForOther(mapperClass, sqlSource, Boolean.class);
    }
}
