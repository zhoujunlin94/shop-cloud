CREATE DATABASE IF NOT EXISTS `product` CHARACTER SET 'utf8mb4';
USE `product`;

drop table IF EXISTS `shop_product`;
CREATE TABLE `shop_product`
(
    `id`      int unsigned AUTO_INCREMENT NOT NULL COMMENT '主键',
    `p_name`  varchar(128)                NOT NULL DEFAULT '' COMMENT '商品名',
    `p_price` int unsigned                NOT NULL DEFAULT 0 COMMENT '商品价格 单位：分',
    `stock`   int unsigned                NOT NULL DEFAULT 0 COMMENT '库存',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '商品表';


insert into shop_product (p_name, p_price, stock)
values ('小米', 200000, 5000),
       ('OPPO', 300000, 5000),
       ('华为', 400000, 5000),
       ('苹果', 500000, 5000);