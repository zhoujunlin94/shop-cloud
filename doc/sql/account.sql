CREATE DATABASE IF NOT EXISTS `account` CHARACTER SET 'utf8mb4';
USE `account`;


drop table IF EXISTS `shop_account`;
CREATE TABLE `shop_account`
(
    `id`         int unsigned AUTO_INCREMENT NOT NULL COMMENT '主键',
    `user_id`    int unsigned                NOT NULL DEFAULT 0 COMMENT '用户id',
    `balance`    int unsigned                NOT NULL DEFAULT 0 COMMENT '用户余额',
    `created_at` datetime                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` datetime                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户账户表';

insert into shop_account (user_id, balance)
values (2, 250000);


CREATE TABLE undo_log
(
    id            BIGINT(20)   NOT NULL AUTO_INCREMENT,
    branch_id     BIGINT(20)   NOT NULL,
    xid           VARCHAR(100) NOT NULL,
    context       VARCHAR(128) NOT NULL,
    rollback_info LONGBLOB     NOT NULL,
    log_status    INT(11)      NOT NULL,
    log_created   DATETIME     NOT NULL,
    log_modified  DATETIME     NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY ux_undo_log (xid, branch_id)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;
