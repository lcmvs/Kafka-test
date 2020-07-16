package com.lcm.test.kafkatest.easy;

import com.lcm.test.kafkatest.pojo.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-14 17:56
 **/
@Component
@Slf4j
public class EasyConsumer {

    @KafkaListener(topics = MyMessage.TOPIC, groupId =  MyMessage.TOPIC+"-group-1" , concurrency = "2")
    public void onMessage1(MyMessage mes) {
        log.info("消费组:{} 消息内容：{}]", 1, mes);
    }

    @KafkaListener(topics = MyMessage.TOPIC, groupId =  MyMessage.TOPIC+"-group-2")
    public void onMessage2(MyMessage mes) {
        log.info("消费组:{} 消息内容：{}]", 2, mes);
    }

}
