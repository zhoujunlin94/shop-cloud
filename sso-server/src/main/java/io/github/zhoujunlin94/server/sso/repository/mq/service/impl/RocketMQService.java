package io.github.zhoujunlin94.server.sso.repository.mq.service.impl;

import io.github.zhoujunlin94.meet.common.util.RequestIdUtil;
import io.github.zhoujunlin94.server.sso.repository.mq.service.MQService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

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
        log.warn("syncSend RocketMQ SendResult:{}", sendResult);
        return sendResult.getSendStatus() == SendStatus.SEND_OK;
    }

    public void asyncSend(String topic, Object payload, BiConsumer<String, Object> successConsumer, BiConsumer<String, Object> exceptionConsumer) {
        log.warn("asyncSend RocketMQ, topic:{}, payload:{}", topic, payload);
        String requestId = MDC.get(RequestIdUtil.REQUEST_ID);
        rocketMQTemplate.asyncSend(topic, payload, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                MDC.put(RequestIdUtil.REQUEST_ID, requestId);
                log.warn("[asyncSend][消息体:{} 发送成功, 结果为:{}]", payload, sendResult);
                successConsumer.accept(topic, payload);
            }

            @Override
            public void onException(Throwable throwable) {
                MDC.put(RequestIdUtil.REQUEST_ID, requestId);
                log.warn("[asyncSend][消息体:{} 发送异常]", payload, throwable);
                exceptionConsumer.accept(topic, payload);
            }

        });
    }

    @Override
    public void sendOneWay(String topic, Object payload) {
        log.warn("sendOneWay RocketMQ, topic:{}, payload:{}", topic, payload);
        rocketMQTemplate.sendOneWay(topic, payload);
    }

}
