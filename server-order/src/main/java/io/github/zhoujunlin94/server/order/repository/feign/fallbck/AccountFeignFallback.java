package io.github.zhoujunlin94.server.order.repository.feign.fallbck;

import io.github.zhoujunlin94.meet.common.exception.CommonErrorCode;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.server.order.repository.feign.client.AccountFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhoujunlin
 * @date 2024/6/9 22:15
 */
@Slf4j
@Component
public class AccountFeignFallback implements AccountFeignClient {

    @Override
    public JsonResponse<Object> reduceBalance(Map<String, Object> requestJson) {
        return JsonResponse.fail(CommonErrorCode.B_NOT_FOUND, null);
    }

}
