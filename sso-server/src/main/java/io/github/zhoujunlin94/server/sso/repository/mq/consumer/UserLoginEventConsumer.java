package io.github.zhoujunlin94.server.sso.repository.mq.consumer;

import io.github.zhoujunlin94.server.sso.dto.LoginLogDTO;
import io.github.zhoujunlin94.server.sso.repository.db.entity.UserLoginLog;
import io.github.zhoujunlin94.server.sso.repository.db.handler.UserLoginLogHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author zhoujunlin
 * @date 2024/7/3 21:47
 */
@Slf4j
@Component
@RequiredArgsConstructor
@RocketMQMessageListener(
        topic = "event_user-login",
        consumerGroup = "RecordLog"
)
public class UserLoginEventConsumer implements RocketMQListener<LoginLogDTO> {

    private final UserLoginLogHandler userLoginLogHandler;

    @Override
    public void onMessage(LoginLogDTO message) {
        UserLoginLog userLoginLog = new UserLoginLog().setUserId(message.getUserId()).setLoginIp(message.getLoginIp());
        userLoginLogHandler.insertSelective(userLoginLog);
    }

}
