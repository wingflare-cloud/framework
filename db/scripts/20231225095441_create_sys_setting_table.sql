-- // create sys_setting table
-- Migration SQL that makes the change goes here.
CREATE TABLE `sys_setting`
(
    `setting_id`     varchar(32)  NOT NULL,
    `state`          tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '设置状态',
    `system_code`    varchar(32)  NOT NULL DEFAULT '' COMMENT '系统代码',
    `setting_code`   varchar(128) NOT NULL DEFAULT '' COMMENT '设置代码',
    `setting_name`   varchar(64)  NOT NULL DEFAULT '' COMMENT '设置名称',
    `setting_value`  text COMMENT '设置值',
    `setting_text`   varchar(256) NOT NULL DEFAULT '' COMMENT '设置文本描述',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`setting_id`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE,
    KEY              `system_name` (`system_code`,`setting_name`) USING BTREE,
    KEY              `system_name2` (`setting_name`) USING BTREE,
    KEY              `system_code` (`system_code`,`setting_code`) USING BTREE,
    KEY              `system_code2` (`setting_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统设置表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `sys_setting`;

