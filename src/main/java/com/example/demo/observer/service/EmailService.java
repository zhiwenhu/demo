package com.example.demo.observer.service;

import com.example.demo.observer.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService implements ApplicationListener<UserRegisterEvent> {

    /**
     * 实现对用户注册监听发送邮件
     * @param event
     */
    @Override
    @Async // 异步执行
    public void onApplicationEvent(UserRegisterEvent event) {
        log.info("[onApplicationEvent][给用户({}) 发送邮件]", event.getUsername());
    }
}
