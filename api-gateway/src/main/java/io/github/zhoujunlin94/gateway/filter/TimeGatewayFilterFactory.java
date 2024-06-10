package io.github.zhoujunlin94.gateway.filter;

import cn.hutool.core.date.StopWatch;
import io.github.zhoujunlin94.meet.common.util.RequestIdUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * @author zhoujunlin
 * @date 2024/6/10 16:16
 */
@Slf4j
@Component
public class TimeGatewayFilterFactory extends AbstractGatewayFilterFactory<TimeGatewayFilterFactory.Config> {

    public TimeGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        // 配置文件中的值映射到Config对象的什么字段
        return Collections.singletonList("enable");
    }

    @Override
    public GatewayFilter apply(Config config) {
        // 返回过滤器   启动时  加入过滤器链
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (!config.enable) {
                    return chain.filter(exchange);
                }
                // 前置处理逻辑
                String requestId = RequestIdUtil.generateRequestId();
                MDC.put(RequestIdUtil.REQUEST_ID, requestId);

                ServerHttpRequest request = exchange.getRequest();
                log.warn("开始访问:{} {}", request.getMethod(), request.getURI());
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();

                MDC.remove(RequestIdUtil.REQUEST_ID);
                return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                    // 后置处理逻辑
                    stopWatch.stop();
                    MDC.put(RequestIdUtil.REQUEST_ID, requestId);

                    log.warn("结束加载请求:{},总用时:{}ms", request.getURI(), stopWatch.getTotalTimeMillis());

                    MDC.remove(RequestIdUtil.REQUEST_ID);
                }));
            }
        };
    }

    @Setter
    @Getter
    public static class Config {
        private boolean enable = false;
    }

}
