DROP TABLE IF EXISTS `base_identity`;


CREATE TABLE `base_identity`
(
    `identity_id`    varchar(32)  NOT NULL,
    `identity_code`  varchar(32)  NOT NULL DEFAULT '' COMMENT '身份代码',
    `org_id`         varchar(32)  NOT NULL DEFAULT '' COMMENT '组织机构id',
    `department_id`  varchar(32)  NOT NULL DEFAULT '' COMMENT '部门id',
    `identity_name`  varchar(256) NOT NULL DEFAULT '' COMMENT '岗位名称',
    `job_level_id`   varchar(32)  NOT NULL DEFAULT '' COMMENT '职级id',
    `created_time`   datetime     NOT NULL,
    `updated_time`   datetime     NOT NULL,
    `create_user`    varchar(32)  NOT NULL DEFAULT '',
    `create_user_id` varchar(32)  NOT NULL DEFAULT '',
    `update_user`    varchar(32)  NOT NULL DEFAULT '',
    `update_user_id` varchar(32)  NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`identity_id`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE,
    KEY              `org_id` (`org_id`) USING BTREE,
    KEY              `job_level_id` (`job_level_id`) USING BTREE,
    KEY              `department_id` (`department_id`) USING BTREE,
    KEY              `identity_name` (`identity_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位身份数据表';

