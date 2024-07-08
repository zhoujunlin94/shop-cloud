package io.github.zhoujunlin94.server.product.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhoujunlin
 * @date 2023年09月23日 11:00
 * @desc
 */
@Data
public class ProductReduceStockDTO {

    /**
     * 商品编号
     */
    @NotNull(message = "商品id不可为空")
    private Integer productId;

    /**
     * 数量
     */
    @NotNull(message = "商品数量不可为空")
    private Integer amount;

}
