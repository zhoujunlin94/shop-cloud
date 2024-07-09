package io.github.zhoujunlin94.server.order.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.BooleanUtil;
import io.github.zhoujunlin94.meet.common.exception.MeetException;
import io.github.zhoujunlin94.server.order.dto.OrderDTO;
import io.github.zhoujunlin94.server.order.repository.db.entity.Order;
import io.github.zhoujunlin94.server.order.repository.db.handler.OrderHandler;
import io.github.zhoujunlin94.server.order.repository.feign.component.AccountFeignComponent;
import io.github.zhoujunlin94.server.order.repository.feign.component.ProductFeignComponent;
import io.github.zhoujunlin94.server.order.repository.feign.dto.ProductDTO;
import io.github.zhoujunlin94.server.order.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author zhoujunlin
 * @date 2024/6/7 19:40
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductFeignComponent productFeignComponent;
    private final AccountFeignComponent accountFeignComponent;

    private final OrderHandler orderHandler;

    @Override
    @GlobalTransactional
    @Transactional(transactionManager = "orderTransactionManager", rollbackFor = Exception.class)
    public OrderDTO createOrder(Integer productId, Integer number, Integer userId) {
        log.info("[createOrder] 当前 XID: {}", RootContext.getXID());
        // find product rpc
        ProductDTO productDTO = productFeignComponent.findById(productId);
        if (Objects.isNull(productDTO)) {
            throw MeetException.meet("未查询到指定商品:" + productId);
        }
        // 扣减库存
        if (BooleanUtil.isFalse(productFeignComponent.reduceStock(productId, number))) {
            throw new RuntimeException("扣除库存失败");
        }
        // 扣减余额
        Integer payPrice = number * productDTO.getProductPrice();
        if (BooleanUtil.isFalse(accountFeignComponent.reduceBalance(userId, payPrice))) {
            throw new RuntimeException("扣除余额失败");
        }

        Order order = new Order().setUserId(userId).setProductId(productId).setProductName(productDTO.getProductName())
                .setProductPrice(productDTO.getProductPrice()).setNumber(number).setPayPrice(payPrice);
        orderHandler.insertSelective(order);
        return BeanUtil.toBean(order, OrderDTO.class);
    }

}
