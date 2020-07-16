package com.lcm.test.kafkatest.pojo;

import lombok.Data;

/**
 * @description:
 * @author: lcm
 * @create: 2020-07-14 17:54
 **/
@Data
public class MyMessage {

    public static final String TOPIC="lcm.mes";

    public static final String TOPIC_RETRY="lcm.mes.2";

    public static final String TOPIC_TRANSACTION_1="lcm.mes.3";

    public static final String TOPIC_TRANSACTION_2="lcm.mes.4";

    private Integer id;

    private Long timestamp=System.currentTimeMillis();

}
