package com.example.demo.controls;

import com.example.demo.observer.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ObserveController {
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/observer/event")
    public String testEvent() {
        userService.register("小明");
        return "successful";
    }
}
