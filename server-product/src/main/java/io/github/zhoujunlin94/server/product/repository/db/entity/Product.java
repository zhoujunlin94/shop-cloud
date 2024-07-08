package io.github.zhoujunlin94.server.product.repository.db.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

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
    @Column(name = "product_name")
    private String productName;

    /**
     * 商品价格 单位：分
     */
    @Column(name = "product_price")
    private Integer productPrice;

    /**
     * 库存
     */
    @Column(name = "stock")
    private Integer stock;

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