package io.github.zhoujunlin94.gateway.service;

import io.github.zhoujunlin94.gateway.web.dto.UserDTO;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author zhoujunlin
 * @date 2024-07-08-16:23
 */
@Slf4j
@Service
public class SsoUserService {

    @Resource
    private WebClient.Builder webBuilder;

    private final static String BASE_URL = "lb://server-sso";

    public Integer getUserId(String token) {
        try {
            Mono<JsonResponse<UserDTO>> result = webBuilder.build()
                    .get().uri(BASE_URL + "/api/v1/user/getUser")
                    .header("Authentication", token)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<JsonResponse<UserDTO>>() {
                    });
            JsonResponse<UserDTO> response = result.toFuture().get();
            return Optional.ofNullable(response).filter(JsonResponse::isOk).map(JsonResponse::getData).map(UserDTO::getId).orElse(null);
        } catch (Exception e) {
            log.error("获取userId失败", e);
        }
        return null;
    }

}
