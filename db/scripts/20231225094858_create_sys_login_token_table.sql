-- // create sys_login_token table
-- Migration SQL that makes the change goes here.
CREATE TABLE `sys_login_token`
(
    `token_id`     varchar(32)  NOT NULL,
    `login_id`     varchar(32)  NOT NULL DEFAULT '' COMMENT '登陆id',
    `token_key`    varchar(512) NOT NULL DEFAULT '' COMMENT 'tokenKey',
    `expire_time`  datetime     NOT NULL COMMENT '过期时间',
    `created_time` datetime     NOT NULL,
    PRIMARY KEY (`token_id`) USING BTREE,
    KEY            `login_id` (`login_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='登陆token表';


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE `sys_login_token`;

