package com.lcm.test.kafkatest.retry;

import com.lcm.test.kafkatest.pojo.MyMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-14 17:55
 **/
@Component
public class RetryProducer {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    /**
     * 同步发送
     * @param id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        MyMessage message = new MyMessage();
        message.setId(id);
        return kafkaTemplate.send(MyMessage.TOPIC_RETRY, message).get();
    }


}
