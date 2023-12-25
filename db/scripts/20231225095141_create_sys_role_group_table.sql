-- // create sys_role_group table
-- Migration SQL that makes the change goes here.
CREATE TABLE `sys_role_group`
(
    `role_group_id`  varchar(32) NOT NULL,
    `state`          tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '角色分组状态',
    `group_name`     varchar(32) NOT NULL DEFAULT '' COMMENT '角色分组名称',
    `group_remark`   varchar(32) NOT NULL DEFAULT '' COMMENT '角色分组备注',
    `created_time`   datetime    NOT NULL,
    `updated_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `update_user`    varchar(32) NOT NULL DEFAULT '',
    `update_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`        int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`role_group_id`) USING BTREE,
    KEY              `group_name` (`group_name`) USING BTREE,
    KEY              `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统角色表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `sys_role_group`;

