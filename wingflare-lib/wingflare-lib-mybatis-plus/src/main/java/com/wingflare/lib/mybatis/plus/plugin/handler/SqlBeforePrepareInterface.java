package com.wingflare.lib.mybatis.plus.plugin.handler;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;

import java.sql.Connection;

public interface SqlBeforePrepareInterface {

    void handle(
            StatementHandler statementHandler,
            MappedStatement mappedStatement,
            Connection connection,
            Integer transactionTimeout
    );

}
