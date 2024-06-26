package io.github.zhoujunlin94.server.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhoujunlin
 * @date 2024-06-26-14:17
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

}
