package io.github.zhoujunlin94.server.product;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhoujunlin
 * @date 2024/6/6 22:31
 */
@SpringBootApplication
@EnableAutoDataSourceProxy
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
