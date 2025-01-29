DROP TABLE IF EXISTS `sys_role`;


CREATE TABLE `sys_role`
(
    `role_id`        varchar(32)   NOT NULL,
    `state`          tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '角色状态',
    `role_group_id`  varchar(32)   NOT NULL DEFAULT '' COMMENT '角色分组id',
    `parent_role_id` varchar(32)   NOT NULL DEFAULT '' COMMENT '父级角色id',
    `role_id_path`   varchar(1024) NOT NULL DEFAULT '' COMMENT '角色id层级路径',
    `role_name`      varchar(32)   NOT NULL DEFAULT '' COMMENT '角色名称',
    `role_remark`    varchar(32)   NOT NULL DEFAULT '' COMMENT '角色备注',
    `created_time`   datetime      NOT NULL,
    `updated_time`   datetime      NOT NULL,
    `create_user`    varchar(32)   NOT NULL DEFAULT '',
    `create_user_id` varchar(32)   NOT NULL DEFAULT '',
    `update_user`    varchar(32)   NOT NULL DEFAULT '',
    `update_user_id` varchar(32)   NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`role_id`) USING BTREE,
    KEY              `role_group_id` (`role_group_id`) USING BTREE,
    KEY              `parent_role_id` (`parent_role_id`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

