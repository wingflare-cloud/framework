package com.wingflare.facade.lib.task;

/**
 * 定时任务常量
 */
public interface ScheduleConstants {

    /**
     * 执行任务类名
     */
    String TASK_CLASS_NAME = "TASK_CLASS_NAME";

    /** 执行目标key */
    String TASK_PROPERTIES = "TASK_PROPERTIES";

    /**
     * 定时任务白名单配置（仅允许访问的包名，如其他需要可以自行添加）
     */
    String[] JOB_WHITELIST_STR = { "com.wingflare", };

    /**
     * 定时任务违规的字符
     */
    String[] JOB_ERROR_STR = { "java.net.URL", "javax.naming.InitialContext", "org.yaml.snakeyaml",
            "org.springframework", "org.apache", };

}
