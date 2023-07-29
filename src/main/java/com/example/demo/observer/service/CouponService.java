package com.example.demo.observer.service;

import com.example.demo.observer.event.UserRegisterEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CouponService {
    @EventListener //设置监听的事件为 UserRegisterEvent
    public void addCoupon(UserRegisterEvent event) {
        log.info("[addCoupon][给用户({}) 发放优惠劵]", event.getUsername());
    }
}
