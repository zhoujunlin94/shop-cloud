CREATE DATABASE IF NOT EXISTS `user` CHARACTER SET 'utf8mb4';
USE `user`;

drop table IF EXISTS `meet_user`;
CREATE TABLE `meet_user`
(
    `id`       int unsigned AUTO_INCREMENT NOT NULL COMMENT '主键',
    `nickname` varchar(64)                 NOT NULL DEFAULT '' COMMENT '昵称',
    `phone`    varchar(16)                 NOT NULL DEFAULT '' COMMENT '手机号',
    `pwd`      varchar(64)                 NOT NULL DEFAULT '' COMMENT '密码(简单点输入密码登录  正常应该是手机验证码登录)',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_phone` (phone)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';

DROP TABLE IF EXISTS `meet_user_login_log`;
CREATE TABLE `meet_user_login_log`
(
    `id`         int unsigned AUTO_INCREMENT NOT NULL COMMENT '主键',
    `user_id`    int unsigned                NOT NULL DEFAULT 0 COMMENT '用户id',
    `login_ip`   varchar(32)                 NOT NULL DEFAULT '' COMMENT '客户端ip',
    `login_time` datetime                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '登录时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4 COMMENT = '用户登录日志';
