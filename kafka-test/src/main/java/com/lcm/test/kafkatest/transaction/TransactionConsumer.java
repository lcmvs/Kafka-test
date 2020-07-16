package com.lcm.test.kafkatest.transaction;

import com.lcm.test.kafkatest.pojo.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-16 10:18
 **/
@Slf4j
@Component
public class TransactionConsumer {

    @KafkaListener(topics = MyMessage.TOPIC_TRANSACTION_1, groupId =  MyMessage.TOPIC_RETRY+"-group-5")
    public void onMessage5(MyMessage mes) {
        log.info("消费组:{} 消息内容：{}]", 5, mes);
    }

}
