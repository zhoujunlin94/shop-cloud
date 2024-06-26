package io.github.zhoujunlin94.server.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import io.github.zhoujunlin94.model.api.dto.product.ProductDTO;
import io.github.zhoujunlin94.server.product.repository.db.handler.ProductHandler;
import io.github.zhoujunlin94.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author zhoujunlin
 * @date 2024/6/6 22:47
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductHandler productHandler;

    @Override
    public ProductDTO findById(Integer productId) {
        return BeanUtil.toBean(productHandler.selectByPrimaryKey(productId), ProductDTO.class);
    }

}
