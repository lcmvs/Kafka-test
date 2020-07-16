package com.lcm.test.kafkatest;

import com.lcm.test.kafkatest.batch.BatchProducer;
import com.lcm.test.kafkatest.easy.EasyProducer;
import com.lcm.test.kafkatest.pojo.MyMessage;
import com.lcm.test.kafkatest.retry.RetryProducer;
import com.lcm.test.kafkatest.transaction.TransactionProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@Slf4j
@SpringBootTest
class KafkaTestApplicationTests {

    @Autowired
    EasyProducer easyProducer;

    @Autowired
    BatchProducer batchProducer;

    @Test
    void synTest() throws ExecutionException, InterruptedException {
        easyProducer.syncSend(1);
        Thread.sleep(2000);
    }

    @Test
    void asynTest() throws InterruptedException {
        easyProducer.asyncSend(1).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("消息发送失败！");
            }

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                log.info("消息发送成功！");
            }
        });
        Thread.sleep(2000);
    }

    @Test
    void asynBatchTest() throws InterruptedException {
        for(int i=0;i<16;i++){
            MyMessage mes=new MyMessage();
            mes.setId(i);
            batchProducer.asyncSend(mes).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
                @Override
                public void onFailure(Throwable ex) {
                    log.error("消息发送失败:{}",mes.getId());
                }

                @Override
                public void onSuccess(SendResult<Object, Object> result) {
                    log.info("消息发送成功:{}",mes.getId());
                }
            });
        }
        Thread.sleep(15000);
    }

    @Autowired
    RetryProducer retryProducer;

    @Test
    void retryTest() throws ExecutionException, InterruptedException {
        retryProducer.syncSend(1);
        Thread.sleep(10000);
    }

    @Autowired
    TransactionProducer transactionProducer;

    @Test
    public void transactionTest() throws InterruptedException {
        MyMessage mes=new MyMessage();
        mes.setId(1);
        transactionProducer.sendTransaction(mes);
        Thread.sleep(2000);
    }

}
