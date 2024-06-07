package io.github.zhoujunlin94.server.order.repository.db.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 商品id
     */
    @Column(name = "p_id")
    private Integer pId;

    /**
     * 下单那一刻的商品名（快照）
     */
    @Column(name = "p_name")
    private String pName;

    /**
     * 下单那一刻的商品价格 单位：分
     */
    @Column(name = "p_price")
    private Integer pPrice;

    /**
     * 购买数量
     */
    @Column(name = "`number`")
    private Integer number;

    private static final long serialVersionUID = 1L;
}