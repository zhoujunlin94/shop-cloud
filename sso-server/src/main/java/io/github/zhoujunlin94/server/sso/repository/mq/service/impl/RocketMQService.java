package io.github.zhoujunlin94.server.sso.repository.mq.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.github.zhoujunlin94.server.sso.repository.mq.service.MQService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhoujunlin
 * @date 2024/7/3 21:31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RocketMQService implements MQService {

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public boolean syncSend(String topic, Object payload) {
        log.warn("syncSend RocketMQ, topic:{}, payload:{}", topic, payload);
        SendResult sendResult = rocketMQTemplate.syncSend(topic, payload);
        log.warn("syncSend RocketMQ SendResult:{}", JSONObject.toJSONString(sendResult));
        return sendResult.getSendStatus() == SendStatus.SEND_OK;
    }

    @Override
    public void sendOneWay(String topic, Object payload) {
        log.warn("sendOneWay RocketMQ, topic:{}, payload:{}", topic, payload);
        rocketMQTemplate.sendOneWay(topic, payload);
    }

}
