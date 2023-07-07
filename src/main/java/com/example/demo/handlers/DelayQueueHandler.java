package com.example.demo.handlers;

import com.alibaba.fastjson.JSON;
import com.example.demo.constants.RabbitConsts;
import com.example.demo.message.MyMessage;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RabbitListener(queues = RabbitConsts.DELAY_QUEUE)
@Component
public class DelayQueueHandler {

    @RabbitHandler
    public void directHandlerManualAck(MyMessage myMessage, Message message, Channel channel){
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("延迟队列，手动ACK，接收消息：{}", JSON.toJSONString(myMessage));
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                log.error("消费失败，重新压入失败", e1);
            }
        }
    }
}
