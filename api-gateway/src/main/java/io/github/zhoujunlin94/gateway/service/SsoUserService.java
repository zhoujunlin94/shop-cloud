package io.github.zhoujunlin94.gateway.service;

import io.github.zhoujunlin94.gateway.feign.client.SsoUserFeignClient;
import io.github.zhoujunlin94.gateway.feign.dto.UserDTO;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author zhoujunlin
 * @date 2024-07-08-16:23
 */
@Service
@RequiredArgsConstructor
public class SsoUserService {

    private final SsoUserFeignClient ssoUserFeignClient;

    public Integer getUserId(String token) {
        JsonResponse<UserDTO> resp = ssoUserFeignClient.getUser(token);
        if (resp.isOk()) {
            return Optional.ofNullable(resp.getData()).map(UserDTO::getId).orElse(null);
        }
        return null;
    }

}
