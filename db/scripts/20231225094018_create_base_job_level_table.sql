-- // create base job level table
-- Migration SQL that makes the change goes here.
CREATE TABLE `base_job_level`
(
    `job_level_id`      varchar(32)  NOT NULL,
    `level_classify_id` varchar(32)  NOT NULL DEFAULT '' COMMENT '职级分类id',
    `level_name`        varchar(256) NOT NULL DEFAULT '' COMMENT '职级名称',
    `root_level`        int(11) NOT NULL DEFAULT '0' COMMENT '职级全局数值',
    `classify_level`    int(11) NOT NULL DEFAULT '0' COMMENT '职级分类数值',
    `created_time`      datetime     NOT NULL,
    `updated_time`      datetime     NOT NULL,
    `create_user`       varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`    varchar(32)  NOT NULL DEFAULT '',
    `update_user`       varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`    varchar(32)  NOT NULL DEFAULT '',
    `is_delete`         tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`           int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`job_level_id`) USING BTREE,
    KEY                 `level_name` (`level_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职级表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `base_job_level`;

