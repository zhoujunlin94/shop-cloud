package io.github.zhoujunlin94.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhoujunlin
 * @date 2024/6/9 22:47
 */
@EnableFeignClients(basePackages = {"io.github.zhoujunlin94.gateway.feign.client"})
@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

}
