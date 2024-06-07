package io.github.zhoujunlin94.server.order.repository.handler;

import io.github.zhoujunlin94.meet.tk_mybatis.handler.TKHandler;
import io.github.zhoujunlin94.server.order.repository.entity.Order;
import io.github.zhoujunlin94.server.order.repository.mapper.OrderMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujunlin
 * @date 2024/6/7 19:39
 */
@Repository
public class OrderHandler extends TKHandler<OrderMapper, Order> {


}
