package io.github.zhoujunlin94.server.product.service;

import io.github.zhoujunlin94.server.product.dto.ProductDTO;

/**
 * @author zhoujunlin
 * @date 2024/6/6
 */
public interface ProductService {

    ProductDTO findById(Integer productId);

    void reduceStock(Integer productId, Integer amount);

}
