package io.github.zhoujunlin94.server.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import io.github.zhoujunlin94.api.order.dto.OrderDTO;
import io.github.zhoujunlin94.api.product.dto.ProductDTO;
import io.github.zhoujunlin94.server.order.repository.entity.Order;
import io.github.zhoujunlin94.server.order.repository.handler.OrderHandler;
import io.github.zhoujunlin94.server.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zhoujunlin
 * @date 2024/6/7 19:40
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderHandler orderHandler;

    @Override
    public OrderDTO createOrder(Integer productId, Integer userId) {
        // todo find product rpc
        ProductDTO productDTO = new ProductDTO();

        Order order = new Order().setUserId(userId).setUserName("测试")
                .setPId(productId).setPName(productDTO.getPName()).setPPrice(productDTO.getPPrice()).setNumber(1);
        orderHandler.insertSelective(order);
        return BeanUtil.toBean(order, OrderDTO.class);
    }

}
