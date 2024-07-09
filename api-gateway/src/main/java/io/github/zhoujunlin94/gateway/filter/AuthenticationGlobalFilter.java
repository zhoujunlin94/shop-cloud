package io.github.zhoujunlin94.gateway.filter;

import cn.hutool.core.util.StrUtil;
import io.github.zhoujunlin94.gateway.service.SsoUserService;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.meet.common.util.RequestIdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author zhoujunlin
 * @date 2024/6/10 09:36
 */
@Slf4j
@Order(Integer.MIN_VALUE + 1)
@Component
@RequiredArgsConstructor
public class AuthenticationGlobalFilter implements GlobalFilter {

    private final SsoUserService ssoUserService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String requestId = request.getHeaders().getFirst(RequestIdUtil.REQUEST_ID);
        if (StrUtil.isBlank(requestId)) {
            requestId = RequestIdUtil.generateRequestId();
        }
        MDC.put(RequestIdUtil.REQUEST_ID, requestId);
        log.warn("鉴权前置过滤器...");
        // <1> 获得 token

        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("Authentication");

        // <2> 如果没有 token，则不进行认证。因为可能是无需认证的 API 接口
        if (StrUtil.isBlank(token)) {
            return chain.filter(exchange);
        }

        // <3> 获取userId
        Integer userId = ssoUserService.getUserId(token);
        ServerHttpResponse response = exchange.getResponse();
        // <4> 通过 token 获取不到 userId，说明认证不通过
        if (Objects.isNull(userId)) {
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            // 响应 401 状态码
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 响应提示
            DataBuffer buffer = response.bufferFactory().wrap(JsonResponse.fail(401, "认证不通过").toString().getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Flux.just(buffer));
        }

        // <6> 认证通过，将 userId 添加到 Header 中
        ServerHttpRequest wrappedRequest = request.mutate().header("X-GATEWAY-UID", String.valueOf(userId)).build();
        return chain.filter(exchange.mutate().request(wrappedRequest).build()).then(Mono.fromRunnable(() -> {
            log.warn("进入鉴权后置过滤器...");
            MDC.remove(RequestIdUtil.REQUEST_ID);
        }));
    }
}
