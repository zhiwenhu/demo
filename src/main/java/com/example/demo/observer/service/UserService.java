package com.example.demo.observer.service;

import com.example.demo.observer.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void register(String username) {
        // 执行注册逻辑
        log.info("[register][执行用户({}) 的注册逻辑]", username);
        // 发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, username));
    }
}
