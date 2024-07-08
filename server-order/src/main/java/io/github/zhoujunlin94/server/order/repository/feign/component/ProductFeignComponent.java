package io.github.zhoujunlin94.server.order.repository.feign.component;

import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.server.order.dto.product.ProductDTO;
import io.github.zhoujunlin94.server.order.repository.feign.ProductFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
        return response.getData();
    }

}
