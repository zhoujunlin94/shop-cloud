package io.github.zhoujunlin94.server.product.repository.db.mapper;

import io.github.zhoujunlin94.meet.tk_mybatis.mapper.TKMapper;
import io.github.zhoujunlin94.server.product.repository.db.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ProductMapper extends TKMapper<Product> {

    @Update("UPDATE shop_product SET stock = stock - #{amount} WHERE id = #{productId} AND stock >= #{amount}")
    int reduceStock(@Param("productId") Integer productId, @Param("amount") Integer amount);

}