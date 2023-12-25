-- // create sys_role_mutex table
-- Migration SQL that makes the change goes here.
CREATE TABLE `sys_role_mutex`
(
    `id`             varchar(32) NOT NULL,
    `role_id`        varchar(32) NOT NULL DEFAULT '',
    `mutex_role_id`  varchar(32) NOT NULL DEFAULT '',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `role_id` (`role_id`) USING BTREE,
    KEY              `mutex_role_id` (`mutex_role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色互斥表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `sys_role_mutex`;

