package io.github.zhoujunlin94.server.sso.repository.mq;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhoujunlin
 * @date 2024/7/1 22:48
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MQService {

    private final RocketMQTemplate rocketMQTemplate;

    public boolean syncSend(String topic, Object payload) {
        SendResult sendResult = rocketMQTemplate.syncSend(topic, JSONObject.toJSONString(payload));
        return sendResult.getSendStatus() == SendStatus.SEND_OK;
    }


}
