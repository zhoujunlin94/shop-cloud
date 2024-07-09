package io.github.zhoujunlin94.server.order.repository.feign.component;

import com.google.common.collect.ImmutableMap;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.server.order.repository.feign.client.ProductFeignClient;
import io.github.zhoujunlin94.server.order.repository.feign.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhoujunlin
 * @date 2024/6/7 22:08
 */
@Component
@RequiredArgsConstructor
public class ProductFeignComponent {

    private final ProductFeignClient productFeignClient;

    public ProductDTO findById(Integer productId) {
        JsonResponse<ProductDTO> response = productFeignClient.findById(productId);
        return response.isOk() ? response.getData() : null;
    }

    public boolean reduceStock(Integer productId, Integer amount) {
        if (Objects.nonNull(productId) && Objects.nonNull(amount) && productId > 0 && amount > 0) {
            Map<String, Object> requestBody = ImmutableMap.of("productId", productId, "amount", amount);
            return productFeignClient.reduceStock(requestBody).isOk();
        }
        return false;
    }

}
