package io.github.zhoujunlin94.server.account;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhoujunlin
 * @date 2024-06-07-10:15
 */
@SpringBootApplication
@EnableAutoDataSourceProxy
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

}
