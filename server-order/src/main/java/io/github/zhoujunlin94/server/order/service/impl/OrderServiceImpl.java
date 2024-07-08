package io.github.zhoujunlin94.server.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import io.github.zhoujunlin94.meet.common.exception.MeetException;
import io.github.zhoujunlin94.server.order.dto.OrderDTO;
import io.github.zhoujunlin94.server.order.repository.db.entity.Order;
import io.github.zhoujunlin94.server.order.repository.db.handler.OrderHandler;
import io.github.zhoujunlin94.server.order.repository.feign.component.ProductFeignComponent;
import io.github.zhoujunlin94.server.order.repository.feign.dto.ProductDTO;
import io.github.zhoujunlin94.server.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author zhoujunlin
 * @date 2024/6/7 19:40
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderHandler orderHandler;
    private final ProductFeignComponent productFeignComponent;

    @Override
    public OrderDTO createOrder(Integer productId, Integer number, Integer userId) {
        // find product rpc
        ProductDTO productDTO = productFeignComponent.findById(productId);
        if (Objects.isNull(productDTO)) {
            throw MeetException.meet("未查询到指定商品:" + productId);
        }

        Order order = new Order().setUserId(userId).setProductId(productId).setProductName(productDTO.getProductName())
                .setProductPrice(productDTO.getProductPrice()).setNumber(number)
                .setPayPrice(productDTO.getProductPrice() * number);
        orderHandler.insertSelective(order);
        return BeanUtil.toBean(order, OrderDTO.class);
    }

}
