package com.lcm.test.kafkatest.transaction;

import com.lcm.test.kafkatest.pojo.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TransactionProducer {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void sendTransaction(MyMessage msg){
        log.info("异步发送事务：{}",msg);
        kafkaTemplate.send(MyMessage.TOPIC_TRANSACTION_1, msg);
        //throw new RuntimeException("测试事务");
        //kafkaTemplate.send(MyMessage.TOPIC_TRANSACTION_2, msg);
    }

}
