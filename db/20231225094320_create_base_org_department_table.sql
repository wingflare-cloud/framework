DROP TABLE IF EXISTS `base_org_department`;


CREATE TABLE `base_org_department`
(
    `department_id`        varchar(32)  NOT NULL,
    `state`                tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '部门状态',
    `org_id`               varchar(32)  NOT NULL DEFAULT '' COMMENT '组织id',
    `parent_department_id` varchar(32)  NOT NULL DEFAULT '' COMMENT '父级部门',
    `department_name`      varchar(256) NOT NULL DEFAULT '' COMMENT '部门名称',
    `role_id`              varchar(32)  NOT NULL DEFAULT '' COMMENT '部门基础角色id',
    `user_id`              varchar(32)  NOT NULL DEFAULT '' COMMENT '部门主要负责人id',
    `created_time`         datetime     NOT NULL,
    `updated_time`         datetime     NOT NULL,
    `create_user`          varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`       varchar(32)  NOT NULL DEFAULT '',
    `update_user`          varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`       varchar(32)  NOT NULL DEFAULT '',
    `is_delete`            tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`              int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`department_id`) USING BTREE,
    KEY                    `created_time` (`created_time`) USING BTREE,
    KEY                    `org_id` (`org_id`) USING BTREE,
    KEY                    `user_id` (`user_id`) USING BTREE,
    KEY                    `parent_department_id` (`parent_department_id`) USING BTREE,
    KEY                    `org_name` (`department_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织部门表';

