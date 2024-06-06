package io.github.zhoujunlin94.server.product.repository.handler;

import io.github.zhoujunlin94.meet.tk_mybatis.handler.TKHandler;
import io.github.zhoujunlin94.server.product.repository.entity.Product;
import io.github.zhoujunlin94.server.product.repository.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

/**
 * @author zhoujunlin
 * @date 2024/6/6 22:39
 */
@Repository
public class ProductHandler extends TKHandler<ProductMapper, Product> {


}
