package io.github.zhoujunlin94.server.order.repository.feign.client;

import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.server.order.repository.feign.fallbck.AccountFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024/6/7
 */
@FeignClient(name = "server-account", path = "/external/api/v1/account", fallback = AccountFeignFallback.class)
public interface AccountFeignClient {

    @PostMapping("/reduceBalance")
    JsonResponse<Object> reduceBalance(@RequestBody Map<String, Object> requestJson);

}
