package io.github.zhoujunlin94.server.xxljob.executor.config;

import io.github.zhoujunlin94.meet.web.interceptor.HttpBaseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private HttpBaseInterceptor httpBaseInterceptor;
    @Resource
    private HttpMessageConverter<Object> fastJsonHttpMessageConverter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 基础http请求拦截器
        registry.addInterceptor(httpBaseInterceptor)
                .excludePathPatterns("/favicon.ico")
        ;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, fastJsonHttpMessageConverter);
    }

}