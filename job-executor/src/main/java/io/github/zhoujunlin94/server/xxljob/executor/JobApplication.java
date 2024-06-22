package io.github.zhoujunlin94.server.xxljob.executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhoujunlin
 * @date 2024/6/22 21:11
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }

}
