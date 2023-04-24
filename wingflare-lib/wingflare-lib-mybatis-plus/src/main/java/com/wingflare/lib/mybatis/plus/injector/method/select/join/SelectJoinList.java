package com.wingflare.lib.mybatis.plus.injector.method.select.join;


import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.wingflare.lib.mybatis.plus.enums.CustomMethod;
import com.wingflare.lib.mybatis.plus.injector.CustomAbstractMethod;
import com.wingflare.lib.mybatis.plus.injector.JoinResultType;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 查询满足条件所有数据
 *
 * @author shaoyuyao
 * @date 2022/8/19 15:02
 */
public class SelectJoinList extends CustomAbstractMethod {

    public SelectJoinList() {
        super(CustomMethod.SELECT_JOIN_LIST.getMethod());
    }

    public SelectJoinList(String methodName) {
        super(methodName);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        CustomMethod customMethod = CustomMethod.SELECT_JOIN_LIST;

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
