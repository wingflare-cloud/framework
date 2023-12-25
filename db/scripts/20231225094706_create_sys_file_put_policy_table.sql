-- // create sys_file_put_policy table
-- Migration SQL that makes the change goes here.
CREATE TABLE `sys_file_put_policy`
(
    `policy_id`       varchar(32)  NOT NULL DEFAULT '',
    `status`          varchar(32)  NOT NULL DEFAULT '' COMMENT '策略状态',
    `conf_key`        varchar(32)  NOT NULL DEFAULT '' COMMENT '上传配置',
    `store_drive`     varchar(32)  NOT NULL DEFAULT '' COMMENT '上传驱动',
    `size_min`        decimal(16, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '文件大小，最小值',
    `size_max`        decimal(16, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '文件大小，最大值',
    `mime_limit`      text         NOT NULL COMMENT '文件mime类型限制',
    `file_id`         varchar(32)  NOT NULL DEFAULT '' COMMENT '文件id',
    `expiration_time` datetime              DEFAULT NULL COMMENT '过期时间',
    `auto_remove`     tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否自动删除',
    `remote_url`      varchar(512) NOT NULL DEFAULT '' COMMENT '远程下载地址',
    `created_time`    datetime     NOT NULL,
    PRIMARY KEY (`policy_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='文件上传策略表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `sys_file_put_policy`;

