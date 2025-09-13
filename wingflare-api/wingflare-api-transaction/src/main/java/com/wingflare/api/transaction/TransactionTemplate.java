package com.wingflare.api.transaction;


/**
 * 标准事务接口
 */
public interface TransactionTemplate {

    <T> T execute(TransactionCallback<T> action);

    void execute(TransactionCallbackVoid action);

    @FunctionalInterface
    interface TransactionCallback<T> {
        T doInTransaction();
    }

    @FunctionalInterface
    interface TransactionCallbackVoid {
        void doInTransaction();
    }

}
