DROP TABLE IF EXISTS `base_org`;


CREATE TABLE `base_org`
(
    `org_id`          bigint(20) unsigned  NOT NULL,
    `org_code`        varchar(32)  NOT NULL DEFAULT '' COMMENT '组织代码',
    `parent_org_id`   bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '父级机构id',
    `state`           tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '组织状态',
    `org_type`        varchar(32)  NOT NULL DEFAULT '' COMMENT '组织类型',
    `org_name`        varchar(256) NOT NULL DEFAULT '' COMMENT '组织名称',
    `simple_org_name` varchar(256) NOT NULL DEFAULT '' COMMENT '组织简称',
    `org_phone`       varchar(64)  NOT NULL DEFAULT '' COMMENT '组织手机或固话号',
    `org_address`     varchar(256) NOT NULL DEFAULT '' COMMENT '详细地址',
    `role_id`         bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '机构基础角色id',
    `user_id`         bigint(20) unsigned  NOT NULL DEFAULT '0' COMMENT '机构主要负责人id',
    `created_time`    datetime     NOT NULL,
    `updated_time`    datetime     NOT NULL,
    `create_user`     varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`  bigint(20) unsigned  NOT NULL DEFAULT '0',
    `update_user`     varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`  bigint(20) unsigned  NOT NULL DEFAULT '0',
    `is_delete`       tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`         int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`org_id`) USING BTREE,
    KEY               `created_time` (`created_time`) USING BTREE,
    KEY               `parent_org_id` (`parent_org_id`) USING BTREE,
    KEY               `user_id` (`user_id`) USING BTREE,
    KEY               `org_type` (`org_type`) USING BTREE,
    KEY               `org_name` (`org_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织机构表';
