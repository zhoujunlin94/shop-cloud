package io.github.zhoujunlin94.server.sso.satoken;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.hutool.core.convert.Convert;
import io.github.zhoujunlin94.meet.common.util.RequestContextUtil;
import io.github.zhoujunlin94.meet.rocketmq.helper.RocketMQHelper;
import io.github.zhoujunlin94.server.sso.dto.LoginLogDTO;
import io.github.zhoujunlin94.server.sso.repository.mq.constant.TopicEnum;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhoujunlin
 * @date 2024-06-26-19:52
 * 原理是事件发布 所以里面的方法最好try catch 防止影响主流程
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserSaTokenListener implements SaTokenListener {

    private final RocketMQHelper rocketMQHelper;

    /**
     * 每次登录时触发
     */
    @Override
    @SneakyThrows
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {
        try {
            LoginLogDTO loginLog = new LoginLogDTO(Convert.toInt(loginId), RequestContextUtil.get().getClientIP());
            rocketMQHelper.sendOneWay(TopicEnum.TOPIC_LOGIN.getTopic(), loginLog);
        } catch (Exception e) {
            log.warn("自定义侦听器实现 doLogin", e);
        }
    }

    /**
     * 每次注销时触发
     */
    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {
    }

    /**
     * 每次被踢下线时触发
     */
    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {
    }

    /**
     * 每次被顶下线时触发
     */
    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {
    }

    /**
     * 每次被封禁时触发
     */
    @Override
    public void doDisable(String loginType, Object loginId, String service, int level, long disableTime) {
    }

    /**
     * 每次被解封时触发
     */
    @Override
    public void doUntieDisable(String loginType, Object loginId, String service) {
    }

    /**
     * 每次二级认证时触发
     */
    @Override
    public void doOpenSafe(String loginType, String tokenValue, String service, long safeTime) {
    }

    /**
     * 每次退出二级认证时触发
     */
    @Override
    public void doCloseSafe(String loginType, String tokenValue, String service) {
    }

    /**
     * 每次创建Session时触发
     */
    @Override
    public void doCreateSession(String id) {
    }

    /**
     * 每次注销Session时触发
     */
    @Override
    public void doLogoutSession(String id) {
    }

    /**
     * 每次Token续期时触发
     */
    @Override
    public void doRenewTimeout(String tokenValue, Object loginId, long timeout) {
    }

}
