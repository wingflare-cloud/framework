package com.wingflare.lib.mybatis.plus.injector.method.select.single;


import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.wingflare.lib.mybatis.plus.constants.Constant;
import com.wingflare.lib.mybatis.plus.enums.CustomMethod;
import com.wingflare.lib.mybatis.plus.injector.CustomAbstractMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据主键判断数据是否存在
 *
 * @author shaoyuyao
 * @date 2022/8/10 15:40
 */
public class SelectExistById extends CustomAbstractMethod {

    public SelectExistById() {
        super(CustomMethod.SELECT_EXIST_BY_ID.getMethod());
    }

    public SelectExistById(String name) {
        super(name);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        CustomMethod customMethod = CustomMethod.SELECT_EXIST_BY_ID;

        String sql = String.format(
                customMethod.getSql(),
                tableInfo.getTableName(),
                tableInfo.getKeyColumn(),
                Constant.ID
        );

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return this.addSelectMappedStatementForOther(mapperClass, sqlSource, Boolean.class);
    }
}
