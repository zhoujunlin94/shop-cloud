package io.github.zhoujunlin94.server.product.service.impl;

import cn.hutool.core.bean.BeanUtil;
import io.github.zhoujunlin94.meet.common.exception.MeetException;
import io.github.zhoujunlin94.server.product.dto.ProductDTO;
import io.github.zhoujunlin94.server.product.repository.db.entity.Product;
import io.github.zhoujunlin94.server.product.repository.db.handler.ProductHandler;
import io.github.zhoujunlin94.server.product.service.ProductService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author zhoujunlin
 * @date 2024/6/6 22:47
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductHandler productHandler;

    @Override
    public ProductDTO findById(Integer productId) {
        return BeanUtil.toBean(productHandler.selectByPrimaryKey(productId), ProductDTO.class);
    }


    @Override
    @Transactional(transactionManager = "productTransactionManager", rollbackFor = Exception.class)
    public void reduceStock(Integer productId, Integer amount) {
        log.info("[reduceStock] 当前 XID: {}", RootContext.getXID());

        // 检查库存
        checkStock(productId, amount);

        log.info("[reduceStock] 开始扣减 {} 库存", productId);
        // 扣减库存
        boolean result = productHandler.reduceStock(productId, amount);

        if (!result) {
            log.warn("[reduceStock] 扣除 {} 库存失败", productId);
            throw MeetException.meet("库存不足,扣除库存失败");
        }
        // 扣除成功
        log.info("[reduceStock] 扣除 {} 库存成功", productId);
    }

    private void checkStock(Integer productId, Integer requiredAmount) {
        log.info("[checkStock] 检查 {} 库存", productId);
        Integer stock = Optional.ofNullable(productHandler.selectByPrimaryKey(productId)).map(Product::getStock).orElse(0);
        if (stock < requiredAmount) {
            log.warn("[checkStock] {} 库存不足，当前库存: {}", productId, stock);
            throw MeetException.meet("库存不足");
        }
    }


}
