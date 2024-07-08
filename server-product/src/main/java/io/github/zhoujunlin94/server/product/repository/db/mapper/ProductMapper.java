package io.github.zhoujunlin94.server.product.repository.db.mapper;

import io.github.zhoujunlin94.meet.tk_mybatis.mapper.TKMapper;
import io.github.zhoujunlin94.server.product.repository.db.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends TKMapper<Product> {


}