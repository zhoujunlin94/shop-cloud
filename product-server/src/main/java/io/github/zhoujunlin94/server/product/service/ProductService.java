package io.github.zhoujunlin94.server.product.service;

import io.github.zhoujunlin94.model.api.dto.product.ProductDTO;

/**
 * @author zhoujunlin
 * @date 2024/6/6
 */
public interface ProductService {

    ProductDTO findById(Integer productId);

}