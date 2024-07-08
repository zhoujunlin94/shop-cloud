package io.github.zhoujunlin94.gateway.feign.client;

import io.github.zhoujunlin94.gateway.feign.dto.UserDTO;
import io.github.zhoujunlin94.gateway.feign.fallback.SsoUserFeignFallBack;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author zhoujunlin
 * @date 2024-07-08-15:57
 */
@FeignClient(name = "server-sso", fallback = SsoUserFeignFallBack.class)
public interface SsoUserFeignClient {

    @GetMapping("/api/v1/user/getUser")
    JsonResponse<UserDTO> getUser(@RequestHeader("Authentication") String token);

}
