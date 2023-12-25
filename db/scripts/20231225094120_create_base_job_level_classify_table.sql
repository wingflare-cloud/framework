-- // create base job level classify table
-- Migration SQL that makes the change goes here.
CREATE TABLE `base_job_level_classify`
(
    `level_classify_id` varchar(32)  NOT NULL,
    `classify_name`     varchar(256) NOT NULL DEFAULT '' COMMENT '分类名',
    `classify_code`     varchar(32)  NOT NULL DEFAULT '' COMMENT '分类代码',
    `created_time`      datetime     NOT NULL,
    `updated_time`      datetime     NOT NULL,
    `create_user`       varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`    varchar(32)  NOT NULL DEFAULT '',
    `update_user`       varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`    varchar(32)  NOT NULL DEFAULT '',
    `is_delete`         tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`           int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`level_classify_id`) USING BTREE,
    KEY                 `classify_name` (`classify_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='职级分类表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `base_job_level_classify`;

