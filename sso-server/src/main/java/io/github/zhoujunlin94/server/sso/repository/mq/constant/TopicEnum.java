package io.github.zhoujunlin94.server.sso.repository.mq.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author zhoujunlin
 * @date 2024/7/1
 */
@Getter
@RequiredArgsConstructor
public enum TopicEnum {

    /**
     * topic枚举
     */
    TOPIC_LOGIN("event_user-login", "用户登录事件"),
    ;

    private final String topic;
    private final String desc;

}
