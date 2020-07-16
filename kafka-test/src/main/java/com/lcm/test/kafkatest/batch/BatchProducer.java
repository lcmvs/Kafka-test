package com.lcm.test.kafkatest.batch;

import com.lcm.test.kafkatest.pojo.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-15 15:22
 **/
@Slf4j
@Component
public class BatchProducer {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public ListenableFuture<SendResult<Object, Object>> asyncSend(MyMessage mes) {
        ListenableFuture<SendResult<Object, Object>> future = kafkaTemplate.send(MyMessage.TOPIC, mes);
        log.info("[异步发送消息：{}]",mes);
        return future;
    }
}
