package com.wingflare.engine.task.server.common.lock.persistence;

import com.wingflare.engine.task.server.common.exception.TaskServerException;
import com.wingflare.task.datasource.template.enums.DbTypeEnum;
import com.wingflare.task.datasource.template.utils.DbUtils;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author xiaowoniu
 * @date 2024-01-11 22:38:36
 * @since 2.6.0
 */
public final class LockStorageFactory {

    private static final List<LockStorage> LOCK_STORAGES = Lists.newArrayList();

    public static void registerLockStorage(LockStorage lockStorage) {
        LOCK_STORAGES.add(lockStorage);
    }

    public static LockStorage getLockStorage() {
        DbTypeEnum db = DbUtils.getDbType();
        return LOCK_STORAGES.stream()
                .filter(lockProvider -> lockProvider.supports(db.getDb()))
                .findFirst().orElseThrow(() -> new TaskServerException("Suitable lock handler not found"));
    }

}
