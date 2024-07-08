package io.github.zhoujunlin94.server.order.repository.feign.component;

import io.github.zhoujunlin94.meet.common.exception.CommonErrorCode;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.server.order.dto.product.ProductDTO;
import io.github.zhoujunlin94.server.order.repository.feign.ProductFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhoujunlin
 * @date 2024/6/9 22:15
 */
@Slf4j
@Component
public class ProductFeignFallback implements ProductFeignClient {

    @Override
    public JsonResponse<ProductDTO> findById(Integer productId) {
        log.warn("商品服务不可用, productId:{}", productId);
        return JsonResponse.fail(CommonErrorCode.B_NOT_FOUND, null);
    }

}
