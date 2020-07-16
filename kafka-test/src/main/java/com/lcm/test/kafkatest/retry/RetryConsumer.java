package com.lcm.test.kafkatest.retry;

import com.lcm.test.kafkatest.pojo.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-15 16:10
 **/
@Slf4j
@Component
public class RetryConsumer {

    @KafkaListener(topics = MyMessage.TOPIC_RETRY, groupId =  MyMessage.TOPIC_RETRY+"-group-4")
    public void onMessage4(MyMessage mes) {
        log.info("消费组:{} 消息内容：{}]", 4, mes);
        throw new RuntimeException("消费组:4,测试消费重试");
    }

    @KafkaListener(topics = MyMessage.TOPIC_RETRY+".DLT", groupId =  MyMessage.TOPIC_RETRY+"-group-4")
    public void onDLTMessage4(MyMessage mes) {
        log.info("死信队列-消费组:{} 消息内容：{}]", 4, mes);
    }

}
