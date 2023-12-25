-- // create sys_staff_identity table
-- Migration SQL that makes the change goes here.
CREATE TABLE `sys_staff_identity`
(
    `id`             varchar(32) NOT NULL,
    `user_id`        varchar(32) NOT NULL DEFAULT '',
    `staff_id`       varchar(32) NOT NULL DEFAULT '',
    `identity_id`    varchar(32) NOT NULL DEFAULT '',
    `created_time`   datetime    NOT NULL,
    `create_user`    varchar(32) NOT NULL DEFAULT '',
    `create_user_id` varchar(32) NOT NULL DEFAULT '',
    `is_delete`      tinyint(1) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE,
    KEY              `user_id` (`user_id`) USING BTREE,
    KEY              `identity_id` (`identity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统员工身份表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `sys_staff_identity`;

