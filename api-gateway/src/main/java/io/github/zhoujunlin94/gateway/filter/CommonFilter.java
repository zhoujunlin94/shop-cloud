package io.github.zhoujunlin94.gateway.filter;

import cn.hutool.core.date.StopWatch;
import io.github.zhoujunlin94.meet.common.util.RequestIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author zhoujunlin
 * <p>
 * 定义全局过滤器，功能如下:
 * 把客户端真实IP通过请求同的方式传递给微服务
 */
@Slf4j
@Component
public class CommonFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        /**
         * pre拦截逻辑
         * 在请求去到微服务之前，
         * 1. 把客户端真实IP通过请求同的方式传递给微服务
         */

        String requestId = RequestIdUtil.generateRequestId();
        MDC.put(RequestIdUtil.REQUEST_ID, requestId);

        ServerHttpRequest request = exchange.getRequest();
        log.warn("开始访问:{} {}", request.getMethod(), request.getURI());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        MDC.remove(RequestIdUtil.REQUEST_ID);

        ServerHttpRequest retRequest = request.mutate().
                header("X-Real-IP", Objects.requireNonNull(request.getRemoteAddress()).getHostString()).
                build();
        return chain.filter(exchange.mutate().request(retRequest).build()).then(Mono.fromRunnable(() -> {
            /**
             * post拦截逻辑
             */
            stopWatch.stop();
            MDC.put(RequestIdUtil.REQUEST_ID, requestId);

            log.warn("结束加载请求:{},总用时:{}ms", request.getURI(), stopWatch.getTotalTimeMillis());

            MDC.remove(RequestIdUtil.REQUEST_ID);
        }));
    }
}
