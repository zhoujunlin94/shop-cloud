package io.github.zhoujunlin94.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * @author zhoujunlin
 * @date 2024/6/10 18:01
 */
@Configuration
public class ApiGatewayConfiguration {

    @PostConstruct
    public void initBlockHandlers() {
        // 配置sentinel限流返回格式
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(JsonResponse.fail("接口被限流了")));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

}
