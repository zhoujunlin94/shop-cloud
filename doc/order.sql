CREATE DATABASE IF NOT EXISTS `order` CHARACTER SET 'utf8mb4';
USE `order`;

drop table IF EXISTS `shop_order`;
CREATE TABLE `shop_order`
(
    `id`        int unsigned AUTO_INCREMENT NOT NULL COMMENT '主键',
    `user_id`   int unsigned                NOT NULL DEFAULT 0 COMMENT '用户id',
    `user_name` varchar(32)                 NOT NULL DEFAULT '' COMMENT '用户名',
    `p_id`      int unsigned                NOT NULL DEFAULT 0 COMMENT '商品id',
    `p_name`    varchar(128)                NOT NULL DEFAULT '' COMMENT '下单那一刻的商品名（快照）',
    `p_price`   int unsigned                NOT NULL DEFAULT 0 COMMENT '下单那一刻的商品价格 单位：分',
    `number`    int unsigned                NOT NULL DEFAULT 0 COMMENT '购买数量',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_p_id` (`p_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '订单表';