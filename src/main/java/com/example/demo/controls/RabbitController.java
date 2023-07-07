package com.example.demo.controls;

import com.example.demo.constants.RabbitConsts;
import com.example.demo.message.MyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RabbitController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接模式发送
     */
    @GetMapping("/sendDirect")
    public String sendDirect(@RequestParam("message") String message) {

            rabbitTemplate.convertAndSend(RabbitConsts.DIRECT_MODE_QUEUE_ONE, new MyMessage(message));
            return "send Direct OK";
    }

    /**
     * 分列模式发送
     */
    @GetMapping("/sendFanout")
    public String sendFanout(@RequestParam("message") String message) {

        rabbitTemplate.convertAndSend(RabbitConsts.FANOUT_MODE_QUEUE, "",new MyMessage(message));
        return "send Fanout OK";
    }

    /**
     * 主题模式发送1
     */
    @GetMapping("/sendTopic1")
    public String sendTopic1(@RequestParam("message") String message) {

        rabbitTemplate.convertAndSend(RabbitConsts.TOPIC_MODE_QUEUE, "queue.aaa.bbb",new MyMessage(message));
        return "send Topic1 OK";
    }

    /**
     * 主题模式发送2
     */
    @GetMapping("/sendTopic2")
    public String sendTopic2(@RequestParam("message") String message) {

        rabbitTemplate.convertAndSend(RabbitConsts.TOPIC_MODE_QUEUE, "ccc.queue",new MyMessage(message));
        return "send Topic2 OK";
    }

    /**
     * 主题模式发送3
     */
    @GetMapping("/sendTopic3")
    public String sendTopic3(@RequestParam("message") String message) {
        rabbitTemplate.convertAndSend(RabbitConsts.TOPIC_MODE_QUEUE, "3.queue",new MyMessage(message));
        return "send Topic2 OK";
    }

    /**
     * 延迟队列发送
     */
    @GetMapping("/sendDelay")
    public String sendDelay() {
        rabbitTemplate.convertAndSend(RabbitConsts.DELAY_MODE_QUEUE, RabbitConsts.DELAY_QUEUE, new MyMessage("delay message, delay 5s, " + new Date().toString()), message -> {
            message.getMessageProperties().setHeader("x-delay", 5000);
            return message;
        });
        rabbitTemplate.convertAndSend(RabbitConsts.DELAY_MODE_QUEUE, RabbitConsts.DELAY_QUEUE, new MyMessage("delay message,  delay 2s, " + new Date().toString()), message -> {
            message.getMessageProperties().setHeader("x-delay", 2000);
            return message;
        });
        rabbitTemplate.convertAndSend(RabbitConsts.DELAY_MODE_QUEUE, RabbitConsts.DELAY_QUEUE, new MyMessage("delay message,  delay 8s, " + new Date().toString()), message -> {
            message.getMessageProperties().setHeader("x-delay", 8000);
            return message;
        });
        return "send delay OK";
    }
}
