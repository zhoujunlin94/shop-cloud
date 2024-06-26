package io.github.zhoujunlin94.server.order.service;


import io.github.zhoujunlin94.model.api.dto.order.OrderDTO;

/**
 * @author zhoujunlin
 * @date 2024-06-07-10:18
 */
public interface OrderService {

    OrderDTO createOrder(Integer productId, Integer userId);

}
