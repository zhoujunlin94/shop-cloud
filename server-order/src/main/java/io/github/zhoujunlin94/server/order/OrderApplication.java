package io.github.zhoujunlin94.server.order;

import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;

/**
 * @author zhoujunlin
 * @date 2024-06-07-10:15
 */
@SpringBootApplication
@EnableFeignClients(basePackages = {"io.github.zhoujunlin94.server.order.repository.feign.client"})
@EnableAspectJAutoProxy(exposeProxy = true)
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public DataSource dataSource(DataSource accountDataSource) {
        return new DataSourceProxy(accountDataSource);
    }

}
