DROP TABLE IF EXISTS `sys_user`;


CREATE TABLE `sys_user`
(
    `user_id`             bigint(20) unsigned  NOT NULL,
    `super_administrator` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否为超管',
    `ban_state`           tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '账户起禁用状态',
    `sex`                 tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '性别',
    `user_channel`        varchar(64)  NOT NULL DEFAULT '' COMMENT '用户注册渠道系统',
    `account_type`        json         NOT NULL COMMENT '账户类型',
    `user_name`           varchar(32)  NOT NULL DEFAULT '' COMMENT '用户名',
    `avatar`              varchar(256) NOT NULL DEFAULT '' COMMENT '用户头像',
    `user_account`        varchar(32)  NOT NULL DEFAULT '' COMMENT '登录账户',
    `user_phone`          char(11)              DEFAULT '' COMMENT '手机号',
    `user_email`          varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱号',
    `user_passwd`         varchar(256) NOT NULL DEFAULT '' COMMENT '账户密码',
    `last_login_ip`       varchar(15)  NOT NULL DEFAULT '' COMMENT '最后登录ip',
    `last_login_time`     datetime              DEFAULT NULL COMMENT '最后登录时间',
    `created_time`        datetime     NOT NULL,
    `updated_time`        datetime     NOT NULL,
    `create_user`         varchar(32)  NOT NULL DEFAULT '',
    `create_user_id`      bigint(20) unsigned  NOT NULL DEFAULT '0',
    `update_user`         varchar(32)  NOT NULL DEFAULT '',
    `update_user_id`      bigint(20) unsigned  NOT NULL DEFAULT '0',
    `is_delete`           tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`             int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`user_id`) USING BTREE,
    KEY                   `user_name` (`user_name`) USING BTREE,
    KEY                   `user_email` (`user_email`) USING BTREE,
    KEY                   `user_account` (`user_account`) USING BTREE,
    KEY                   `user_phone` (`user_phone`) USING BTREE,
    KEY                   `created_time` (`created_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='系统用户表';

