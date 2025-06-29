DROP TABLE IF EXISTS `wf_ws_webhook`;


CREATE TABLE `wf_ws_webhook`
(
    `id`           bigint(20) unsigned  NOT NULL,
    `server_name`  varchar(32)  NOT NULL DEFAULT '' COMMENT '服务名',
    `host`         varchar(256)  NOT NULL DEFAULT '' COMMENT '主机',
    `path`         varchar(256) NOT NULL DEFAULT '' COMMENT '请求路径',
    `topic`        varchar(256) NOT NULL DEFAULT '' COMMENT '频道名',
    `enable_ssl`   tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否启用ssl',
    `created_time` datetime     NOT NULL,
    `updated_time` datetime     NOT NULL,
    `create_user`    varchar(32)   NOT NULL DEFAULT '',
    `create_user_id` bigint(20) unsigned   NOT NULL DEFAULT '0',
    `update_user`    varchar(32)   NOT NULL DEFAULT '',
    `update_user_id` bigint(20) unsigned   NOT NULL DEFAULT '0',
    `is_delete`    tinyint(1) unsigned NOT NULL DEFAULT '0',
    `version`      int(10) unsigned NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    KEY            `server_name` (`server_name`,`path`),
    KEY            `topic` (`topic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='ws webhook信息表';

