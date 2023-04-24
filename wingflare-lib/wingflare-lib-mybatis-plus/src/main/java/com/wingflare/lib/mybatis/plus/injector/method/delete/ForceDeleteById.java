package com.wingflare.lib.mybatis.plus.injector.method.delete;


import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.wingflare.lib.mybatis.plus.enums.CustomMethod;
import com.wingflare.lib.mybatis.plus.injector.CustomAbstractMethod;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据主键物理删除数据
 *
 * @author shaoyuyao
 * @date 2022/8/10 19:30
 */
public class ForceDeleteById extends CustomAbstractMethod {

    public ForceDeleteById() {
        super(CustomMethod.FORCE_DELETE_BY_ID.getMethod());
    }

    public ForceDeleteById(String name) {
        super(name);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        CustomMethod customMethod = CustomMethod.FORCE_DELETE_BY_ID;

        String sql = String.format(
                customMethod.getSql(),
                tableInfo.getTableName(),
                tableInfo.getKeyColumn(),
                tableInfo.getKeyProperty()
        );

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, Object.class);

        return this.addDeleteMappedStatement(mapperClass, sqlSource);
    }
}
