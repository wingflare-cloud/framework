package com.wingflare.lib.mybatis.plus.injector.method.select.join;


import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.wingflare.lib.mybatis.plus.enums.CustomMethod;
import com.wingflare.lib.mybatis.plus.injector.CustomAbstractMethod;
import com.wingflare.lib.mybatis.plus.injector.JoinResultType;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * @author shaoyuyao
 * @date 2022/8/24 16:10
 */
public class SelectJoinPage extends CustomAbstractMethod {

    public SelectJoinPage() {
        super(CustomMethod.SELECT_JOIN_PAGE.getMethod());
    }

    /**
     * @param name 方法名
     * @since 3.5.0
     */
    public SelectJoinPage(String name) {
        super(name);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        CustomMethod customMethod = CustomMethod.SELECT_JOIN_PAGE;

        String sql = String.format(customMethod.getSql(),
                sqlFirst(),
                sqlDistinct(),
                sqlSelectColumns(tableInfo, true),
                tableInfo.getTableName(),
                sqlMasterTableAlias(),
                sqlJoin(),
                sqlWhereEntityWrapper(true, tableInfo),
                sqlOrderBy(tableInfo),
                sqlComment()
        );

        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        return this.addSelectMappedStatementForOther(mapperClass, sqlSource, JoinResultType.class);
    }
}
