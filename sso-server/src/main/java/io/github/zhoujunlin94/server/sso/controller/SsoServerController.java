package io.github.zhoujunlin94.server.sso.controller;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.sso.config.SaSsoServerConfig;
import cn.dev33.satoken.sso.processor.SaSsoServerProcessor;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.util.SaResult;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import io.github.zhoujunlin94.meet.common.pojo.JsonResponse;
import io.github.zhoujunlin94.server.sso.dto.UserDTO;
import io.github.zhoujunlin94.server.sso.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhoujunlin
 * @date 2024/6/29 11:32
 */
@Slf4j
@RestController
public class SsoServerController {

    /**
     * SSO-Server端：处理所有SSO相关请求
     */
    @RequestMapping("/sso/*")
    public Object ssoRequest() {
        Object ret = SaSsoServerProcessor.instance.dister();
        if (ret instanceof SaResult) {
            return BeanUtil.toBean(ret, JsonResponse.class);
        }
        return ret;
    }

    /**
     * 配置SSO相关参数
     */
    @Autowired
    private void configSso(SaSsoServerConfig ssoServer, UserService userService) {
        // 配置：未登录时返回的View
        ssoServer.notLoginView = () -> new ModelAndView("/sso-auth.html");

        // 配置：登录处理函数
        ssoServer.doLoginHandle = (name, pwd) -> {
            String phone = SaHolder.getRequest().getParam("phone");
            try {
                SaTokenInfo saTokenInfo = userService.login(new UserDTO().setPhone(phone).setPwd(pwd));
                return JsonResponse.ok(saTokenInfo.getTokenValue());
            } catch (Exception ignore) {
            }
            return JsonResponse.fail("登录失败！");
        };


        // 配置 Http 请求处理器 （在模式三的单点注销功能下用到，如不需要可以注释掉）
        ssoServer.sendHttp = url -> {
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
