package io.github.zhoujunlin94.server.order.repository.feign.client;

import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.server.order.repository.feign.dto.ProductDTO;
import io.github.zhoujunlin94.server.order.repository.feign.fallbck.ProductFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024/6/7
 */
@FeignClient(name = "server-product", fallback = ProductFeignFallback.class)
public interface ProductFeignClient {

    @GetMapping("/internal/api/v1/product/findById")
    JsonResponse<ProductDTO> findById(@RequestParam("productId") Integer productId);

    @PostMapping("/external/api/v1/product/reduceStock")
    JsonResponse<Object> reduceStock(@RequestBody Map<String, Object> requestJson);

}
