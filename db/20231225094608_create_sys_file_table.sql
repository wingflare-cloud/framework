DROP TABLE IF EXISTS `sys_file`;


CREATE TABLE `sys_file`
(
    `file_id`         varchar(32)  NOT NULL,
    `state`           varchar(32)  NOT NULL DEFAULT '' COMMENT '文件状态',
    `store_drive`     varchar(32)  NOT NULL DEFAULT '' COMMENT '存储驱动，字典storeDrive',
    `conf_key`        varchar(32)           DEFAULT '' COMMENT '配置key',
    `md5_hash`        char(32)     NOT NULL DEFAULT '' COMMENT '文件md5值',
    `sha256_hash`     char(32)     NOT NULL DEFAULT '' COMMENT '文件sha256值',
    `path`            varchar(256) NOT NULL DEFAULT '' COMMENT '文件存储路径',
    `domain`          varchar(256) NOT NULL DEFAULT '' COMMENT '文件访问域名',
    `size`            decimal(10, 2) unsigned NOT NULL DEFAULT '0.00' COMMENT '文件大小，单位kb',
    `expiration_time` varchar(32)           DEFAULT NULL COMMENT '过期时间',
    `created_time`    datetime     NOT NULL,
    `updated_time`    datetime     NOT NULL,
    `create_user`     varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`  varchar(32)  NOT NULL DEFAULT '',
    `update_user`     varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`  varchar(32)  NOT NULL DEFAULT '',
    `is_delete`       tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`         int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`file_id`) USING BTREE,
    KEY               `md5_hash` (`md5_hash`,`sha256_hash`) USING BTREE,
    KEY               `sha256_hash` (`sha256_hash`) USING BTREE,
    KEY               `store_drive` (`store_drive`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统文件表';

