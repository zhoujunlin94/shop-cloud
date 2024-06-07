package io.github.zhoujunlin94.api.product.dto;

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
    private String pName;

    /**
     * 商品价格 单位：分
     */
    private Integer pPrice;

    /**
     * 库存
     */
    private Integer stock;

}
