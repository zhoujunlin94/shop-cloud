package io.github.zhoujunlin94.api.order.dto;

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
     * 用户名
     */
    private String userName;

    /**
     * 商品id
     */
    private Integer pId;

    /**
     * 下单那一刻的商品名（快照）
     */
    private String pName;

    /**
     * 下单那一刻的商品价格 单位：分
     */
    private Integer pPrice;

    /**
     * 购买数量
     */
    private Integer number;

}
