CREATE DATABASE IF NOT EXISTS `product` CHARACTER SET 'utf8mb4';
USE `product`;

drop table IF EXISTS `shop_product`;
CREATE TABLE `shop_product`
(
    `id`            int unsigned AUTO_INCREMENT NOT NULL COMMENT '主键',
    `product_name`  varchar(128)                NOT NULL DEFAULT '' COMMENT '商品名',
    `product_price` int unsigned                NOT NULL DEFAULT 0 COMMENT '商品价格 单位：分',
    `stock`         int unsigned                NOT NULL DEFAULT 0 COMMENT '库存',
    `created_at`    datetime                    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    datetime                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT = '商品表';

insert into shop_product (product_name, product_price, stock)
values ('小米', 200000, 5000),
       ('OPPO', 300000, 5000),
       ('华为', 400000, 5000),
       ('苹果', 500000, 5000);

drop table IF EXISTS `undo_log`;
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