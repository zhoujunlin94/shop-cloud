package io.github.zhoujunlin94.server.sso.client.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author zhoujunlin
 * @date 2024/6/29 21:01
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class SsoClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoClientApplication.class, args);

        System.out.println("测试访问应用端一: http://sa-sso-client1.com:8591");
        System.out.println("测试访问应用端二: http://sa-sso-client2.com:8591");
        System.out.println("测试访问应用端三: http://sa-sso-client3.com:8591");
    }

}
