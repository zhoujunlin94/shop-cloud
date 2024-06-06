package io.github.zhoujunlin94.server.product.repository.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 商品表
 */
@Data
@Accessors(chain = true)
@Table(name = "shop_product")
public class Product implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 商品名
     */
    @Column(name = "p_name")
    private String pName;

    /**
     * 商品价格 单位：分
     */
    @Column(name = "p_price")
    private Integer pPrice;

    /**
     * 库存
     */
    @Column(name = "stock")
    private Integer stock;

    private static final long serialVersionUID = 1L;
}