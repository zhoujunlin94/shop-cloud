package io.github.zhoujunlin94.server.sso.client.app.controller;

import cn.dev33.satoken.sso.config.SaSsoClientConfig;
import cn.dev33.satoken.sso.processor.SaSsoClientProcessor;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoujunlin
 * @date 2024/6/29 21:05
 */
@Slf4j
@RestController
public class SsoClientController {

    @RequestMapping("/")
    public String index() {
        String str = "<h2>Sa-Token SSO-Client 应用端</h2>" +
                "<p>当前会话是否登录：" + StpUtil.isLogin() + "</p>" +
                "<p><a href=\"javascript:location.href='/sso/login?back=' + encodeURIComponent(location.href);\">登录</a> " +
                "<a href='/sso/logout?back=self'>注销</a></p>";
        return str;
    }

    /**
     * SSO-Client端：处理所有SSO相关请求
     * http://{host}:{port}/sso/login          -- Client端登录地址，接受参数：back=登录后的跳转地址
     * http://{host}:{port}/sso/logout         -- Client端单点注销地址（isSlo=true时打开），接受参数：back=注销后的跳转地址
     * http://{host}:{port}/sso/logoutCall     -- Client端单点注销回调地址（isSlo=true时打开），此接口为框架回调，开发者无需关心
     */
    @RequestMapping("/sso/*")
    public Object ssoRequest() {
        return SaSsoClientProcessor.instance.dister();
    }

    @Autowired
    private void configSso(SaSsoClientConfig ssoClient) {
        // 配置 Http 请求处理器
        ssoClient.sendHttp = url -> {
            try {
                System.out.println("------ 发起请求：" + url);
                String resStr = HttpUtil.get(url);
                System.out.println("------ 请求结果：" + resStr);
                return resStr;
            } catch (Exception e) {
                log.warn("请求:{}出错", url, e);
                return null;
            }
        };
    }

}
