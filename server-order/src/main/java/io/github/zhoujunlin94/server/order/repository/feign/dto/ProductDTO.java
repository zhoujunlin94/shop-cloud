package io.github.zhoujunlin94.server.order.repository.feign.dto;

import lombok.Data;

/**
 * @author zhoujunlin
 * @date 2024/6/6 22:48
 */
@Data
public class ProductDTO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品价格 单位：分
     */
    private Integer productPrice;

    /**
     * 库存
     */
    private Integer stock;

}
