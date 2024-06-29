package io.github.zhoujunlin94.server.user.app.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhoujunlin
 * @date 2024-06-26-15:15
 */
@Configuration
public class SaTokenWebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
        registry.addInterceptor(new SaInterceptor(handle -> {
            SaRouter.match("/**")
                    .notMatch("/api/v1/user/register", "/api/v1/user/login", "/sso/**/*")
                    .check(r -> StpUtil.checkLogin()).stop();
        }).isAnnotation(false)).addPathPatterns("/**");
    }

}
