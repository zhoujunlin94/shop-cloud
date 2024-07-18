package io.github.zhoujunlin94.server.common.app.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author zhoujunlin
 * @date 2023年07月09日 18:46
 */
@Configuration
@EnableKnife4j
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .enable(true).select()
                //这里指定扫描包路径
                .apis(RequestHandlerSelectors.basePackage("io.github.zhoujunlin94.server.common.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("server-common")
                .description("公共服务")
                .contact(new Contact("zhoujunlin", "https://github.com/zhoujunlin94/shop-cloud", "zhoujunlin.work@outlook.com"))
                .version("1.0.0")
                .build();
    }

}
