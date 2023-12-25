-- // create sys_login_info table
-- Migration SQL that makes the change goes here.
CREATE TABLE `sys_login_info`
(
    `login_id`      varchar(32)  NOT NULL,
    `system_code`   varchar(32)  NOT NULL DEFAULT '系统code',
    `user_id`       varchar(32)  NOT NULL DEFAULT '' COMMENT '用户id',
    `org_id`        varchar(32)  NOT NULL DEFAULT '' COMMENT '机构id',
    `identity_id`   varchar(32)  NOT NULL DEFAULT '' COMMENT '身份id',
    `refresh_token` varchar(512) NOT NULL DEFAULT '' COMMENT 'refresh_token',
    `user_agent`    varchar(512) NOT NULL DEFAULT '' COMMENT 'ua',
    `ipaddr`        varchar(15)  NOT NULL DEFAULT '' COMMENT '登陆ip',
    `expire_time`   datetime     NOT NULL COMMENT '过期时间',
    `created_time`  datetime     NOT NULL,
    PRIMARY KEY (`login_id`) USING BTREE,
    KEY             `system_code` (`system_code`) USING BTREE,
    KEY             `user_id` (`user_id`) USING BTREE,
    KEY             `org_id` (`org_id`) USING BTREE,
    KEY             `identity_id` (`identity_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登陆信息表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `sys_login_info`;

