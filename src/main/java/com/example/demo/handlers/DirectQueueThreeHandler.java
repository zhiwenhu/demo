package com.example.demo.handlers;

import com.alibaba.fastjson.JSON;
import com.example.demo.constants.RabbitConsts;
import com.example.demo.message.MyMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@RabbitListener(queues = RabbitConsts.QUEUE_THREE)
@Component
public class DirectQueueThreeHandler {
    /**
     * spring.rabbitmq.listener.direct.acknowledge-mode: auto 自动ack消费
     * @param message
     */
    @RabbitHandler
    public void directHandlerAutoAck(MyMessage message) {
        log.info("直接队列3处理器，接收消息：{}", JSON.toJSONString(message));
    }
}
