package io.github.zhoujunlin94.server.order.repository.feign;

import io.github.zhoujunlin94.api.product.dto.ProductDTO;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.server.order.repository.feign.component.ProductFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhoujunlin
 * @date 2024/6/7
 */
@FeignClient(name = "server-product", path = "/api/v1/product", fallback = ProductFeignFallback.class)
public interface ProductFeignClient {

    @GetMapping("/findById")
    JsonResponse<ProductDTO> findById(@RequestParam("productId") Integer productId);

}
