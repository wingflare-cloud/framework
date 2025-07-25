DROP TABLE IF EXISTS `sys_dict`;


CREATE TABLE `sys_dict`
(
    `dict_id`        bigint(20) unsigned  NOT NULL,
    `state`          tinyint(1) unsigned NOT NULL DEFAULT '0',
    `dict_type`      varchar(32)  NOT NULL DEFAULT '' COMMENT '字典类型',
    `dict_code`      varchar(128) NOT NULL DEFAULT '' COMMENT '字典代码',
    `dict_name`      varchar(64)  NOT NULL DEFAULT '' COMMENT '字典名称',
    `dict_value`     varchar(256) NOT NULL DEFAULT '' COMMENT '字典值',
    `dict_text`      varchar(256) NOT NULL DEFAULT '' COMMENT '字典文本',
    `sort`           int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序值',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` bigint(20) unsigned  NOT NULL DEFAULT '0',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` bigint(20) unsigned  NOT NULL DEFAULT '0',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`dict_id`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE,
    KEY              `dict_name2` (`dict_name`) USING BTREE,
    KEY              `dict_code2` (`dict_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统字典表';

