package io.github.zhoujunlin94.server.order.repository.db.mapper;

import io.github.zhoujunlin94.meet.tk_mybatis.mapper.TKMapper;
import io.github.zhoujunlin94.server.order.repository.db.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends TKMapper<Order> {

}