package io.github.zhoujunlin94.server.order.repository.feign.component;

import com.google.common.collect.ImmutableMap;
import io.github.zhoujunlin94.server.order.repository.feign.client.AccountFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

/**
 * @author zhoujunlin
 * @date 2024/7/9 21:21
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AccountFeignComponent {

    private final AccountFeignClient accountFeignClient;

    public boolean reduceBalance(Integer userId, Integer reducePrice) {
        if (Objects.nonNull(userId) && Objects.nonNull(reducePrice)) {
            Map<String, Object> requestBody = ImmutableMap.of("userId", userId, "reducePrice", reducePrice);
            return accountFeignClient.reduceBalance(requestBody).isOk();
        }
        return false;
    }

}
