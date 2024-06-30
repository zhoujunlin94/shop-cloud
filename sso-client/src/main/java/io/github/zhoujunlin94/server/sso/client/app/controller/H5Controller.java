package io.github.zhoujunlin94.server.sso.client.app.controller;

import cn.dev33.satoken.sso.model.SaCheckTicketResult;
import cn.dev33.satoken.sso.processor.SaSsoClientProcessor;
import cn.dev33.satoken.sso.template.SaSsoUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author zhoujunlin
 * @date 2024/6/30 15:08
 * 前后台分离架构下集成SSO所需的代码 （SSO-Client端）
 */
@RestController
public class H5Controller {

    @RequestMapping("/sso/isLogin")
    public boolean isLogin() {
        return StpUtil.isLogin();
    }

    @RequestMapping("/sso/getSsoAuthUrl")
    public String getSsoAuthUrl(@RequestParam @NotBlank String clientLoginUrl) {
        return SaSsoUtil.buildServerAuthUrl(clientLoginUrl, "");
    }

    @RequestMapping("/sso/doLoginByTicket")
    public String doLoginByTicket(@RequestParam @NotBlank String ticket) {
        SaCheckTicketResult ctr = SaSsoClientProcessor.instance.checkTicket(ticket, "/sso/doLoginByTicket");
        StpUtil.login(ctr.loginId, ctr.remainSessionTimeout);
        return StpUtil.getTokenValue();
    }

}
