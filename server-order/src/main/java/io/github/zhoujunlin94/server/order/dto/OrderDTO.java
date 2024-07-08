package io.github.zhoujunlin94.server.order.dto;

import lombok.Data;

/**
 * @author zhoujunlin
 * @date 2024/6/7 19:44
 */
@Data
public class OrderDTO {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 下单那一刻的商品名（快照）
     */
    private String productName;

    /**
     * 下单那一刻的商品价格 单位：分
     */
    private Integer productPrice;

    /**
     * 购买数量
     */
    private Integer number;

    /**
     * 实际支付价格 单位：分
     */
    private Integer payPrice;

}
