package io.github.zhoujunlin94.server.sso.repository.mq.service;

/**
 * @author zhoujunlin
 * @date 2024/7/1 22:48
 */
public interface MQService {

    boolean syncSend(String topic, Object payload);

    void sendOneWay(String topic, Object payload);

}
