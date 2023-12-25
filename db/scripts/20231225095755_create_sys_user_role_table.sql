-- // create sys_user_role table
-- Migration SQL that makes the change goes here.
CREATE TABLE `sys_user_role`
(
    `id`             varchar(32) NOT NULL,
    `user_id`        varchar(32) NOT NULL DEFAULT '',
    `system_code`    varchar(32) NOT NULL DEFAULT '',
    `role_id`        varchar(32) NOT NULL DEFAULT '',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `user_id` (`user_id`) USING BTREE,
    KEY              `system_role_id` (`system_code`,`role_id`) USING BTREE,
    KEY              `role_id` (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户角色表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `sys_user_role`;

