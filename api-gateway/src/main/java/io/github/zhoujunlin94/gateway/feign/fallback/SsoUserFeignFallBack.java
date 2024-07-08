package io.github.zhoujunlin94.gateway.feign.fallback;

import io.github.zhoujunlin94.gateway.feign.client.SsoUserFeignClient;
import io.github.zhoujunlin94.gateway.feign.dto.UserDTO;
import io.github.zhoujunlin94.meet.common.exception.CommonErrorCode;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhoujunlin
 * @date 2024-07-08-16:02
 */
@Slf4j
@Component
public class SsoUserFeignFallBack implements SsoUserFeignClient {

    @Override
    public JsonResponse<UserDTO> getUser(String token) {
        return JsonResponse.fail(CommonErrorCode.B_NOT_FOUND, null);
    }

}
