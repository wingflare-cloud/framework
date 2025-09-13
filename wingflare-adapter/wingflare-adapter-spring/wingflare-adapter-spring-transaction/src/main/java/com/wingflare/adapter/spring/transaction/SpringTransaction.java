package com.wingflare.adapter.spring.transaction;

import com.wingflare.api.transaction.TransactionTemplate;

/**
 * spring事务实现
 */
public final class SpringTransaction implements TransactionTemplate {

    private final org.springframework.transaction.support.TransactionTemplate template;

    public SpringTransaction(org.springframework.transaction.support.TransactionTemplate template) {
        this.template = template;
    }

    @Override
    public <T> T execute(TransactionCallback<T> action) {
        return template.execute(status -> action.doInTransaction());
    }

    @Override
    public void execute(TransactionCallbackVoid action) {
        template.execute(status -> {
            action.doInTransaction();
            return null;
        });
    }
}
