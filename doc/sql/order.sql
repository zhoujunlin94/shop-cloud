CREATE DATABASE IF NOT EXISTS `order` CHARACTER SET 'utf8mb4';
USE `order`;

drop table IF EXISTS `shop_order`;
CREATE TABLE `shop_order`
(
    `id`            int unsigned AUTO_INCREMENT NOT NULL COMMENT '主键',
    `user_id`       int unsigned                NOT NULL DEFAULT 0 COMMENT '用户id',
    `product_id`    int unsigned                NOT NULL DEFAULT 0 COMMENT '商品id',
    `product_name`  varchar(128)                NOT NULL DEFAULT '' COMMENT '下单那一刻的商品名（快照）',
    `product_price` int unsigned                NOT NULL DEFAULT 0 COMMENT '下单那一刻的商品价格 单位：分',
    `number`        int unsigned                NOT NULL DEFAULT 0 COMMENT '购买数量',
    `pay_price`     int unsigned                NOT NULL DEFAULT 0 COMMENT '实际支付价格 单位：分',
    `created_at`    datetime                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    datetime                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_p_id` (`product_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '订单表';


drop table IF EXISTS `undo_log`;
CREATE TABLE `undo_log`
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