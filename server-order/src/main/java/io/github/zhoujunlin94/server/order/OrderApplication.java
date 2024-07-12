package io.github.zhoujunlin94.server.order;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhoujunlin
 * @date 2024-06-07-10:15
 */
@SpringBootApplication
@EnableAutoDataSourceProxy
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableFeignClients(basePackages = {"io.github.zhoujunlin94.server.order.repository.feign.client"})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
