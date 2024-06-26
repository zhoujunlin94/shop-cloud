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

