-- // create sys_application table
-- Migration SQL that makes the change goes here.
CREATE TABLE `sys_application`
(
    `app_id`         varchar(32)  NOT NULL,
    `app_status`     varchar(32)  NOT NULL DEFAULT '' COMMENT '应用状态',
    `app_type`       varchar(32)  NOT NULL DEFAULT '' COMMENT '应用类型',
    `app_name`       varchar(32)  NOT NULL DEFAULT '' COMMENT '应用名称',
    `app_icon`       varchar(512) NOT NULL DEFAULT '' COMMENT '应用图标',
    `website_url`    varchar(512) NOT NULL DEFAULT '' COMMENT '应用官网地址',
    `merchant_id`    varchar(32)  NOT NULL DEFAULT '' COMMENT '商户id',
    `service_mch_id` varchar(32)  NOT NULL DEFAULT '' COMMENT '服务商商户id',
    `service_app_id` varchar(32)  NOT NULL DEFAULT '' COMMENT '服务商appid',
    `remark`         varchar(256) NOT NULL DEFAULT '' COMMENT '应用描述',
    `created_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`app_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='开放平台应用表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `sys_application`;

