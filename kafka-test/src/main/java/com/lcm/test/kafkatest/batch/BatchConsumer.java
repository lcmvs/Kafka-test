package com.lcm.test.kafkatest.batch;

import com.lcm.test.kafkatest.pojo.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-15 15:41
 **/
@Slf4j
@Component
public class BatchConsumer {

    @KafkaListener(topics = MyMessage.TOPIC, groupId =  MyMessage.TOPIC+"-group-3")
    public void onMessage3(List<MyMessage> list) {
        log.info("消费组:{} ,消费数量:{},消息内容：{}]", 3,list.size(), list);
    }

}
