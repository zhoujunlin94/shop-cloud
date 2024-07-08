package io.github.zhoujunlin94.server.order.repository.db.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 订单表
 */
@Data
@Accessors(chain = true)
@Table(name = "shop_order")
public class Order implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 商品id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 下单那一刻的商品名（快照）
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 下单那一刻的商品价格 单位：分
     */
    @Column(name = "product_price")
    private Integer productPrice;

    /**
     * 购买数量
     */
    @Column(name = "`number`")
    private Integer number;

    /**
     * 实际支付价格 单位：分
     */
    @Column(name = "pay_price")
    private Integer payPrice;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}