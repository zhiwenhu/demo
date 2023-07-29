package com.example.demo.controls;

import com.example.demo.annotation.RateLimiter;
import com.example.demo.entity.UserBean;
import com.example.demo.enums.Gender;
import com.example.demo.service.UserServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MyController {
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/user")
    public String getUser() {
        UserBean myUser = new UserBean();
        myUser.setName("Mary");
        myUser.setGender(Gender.FEMALE);
        myUser.setAge(18);

        return myUser.toString();
    }

    @RateLimiter(value = 2, key = "自定义key")
    @GetMapping("/allUser")
    public List getAllUser() {
        log.info("【getAllUser】被执行了。。。。。");
        List users = userServiceImpl.getAllUser();
        return users;
    }

    @RateLimiter(value = 5)
    @GetMapping("/getUser")
    public UserBean getUser(@RequestParam("name") String name) {
        log.info("【getUser】被执行了。。。。。");
        UserBean usr = userServiceImpl.getOneUser(name);
         if (usr == null) {
             throw new RuntimeException("no user " + name);
         }
         return usr;
    }

    @GetMapping("/updateUser1")
    public String updateUser(@RequestParam("name") String name, @RequestParam("age") String age) {
        int ageTo = 0;

        if (age != null) {
            try {
                ageTo = Integer.valueOf(age);
            } catch (NumberFormatException e) {
                return "age must be int value.";
            }
            if (ageTo <= 0) {
                return "age must be > 0";
            }
        }
        if (name != null) {
            UserBean users = userServiceImpl.getOneUser(name);
            if (users != null) {
                userServiceImpl.updateUser(ageTo, name);
            } else {
                return "no user:" + name;
            }
        }
        return "update success";
    }

    @RequestMapping("/updateUser2")
    public String updateUser(HttpServletRequest request) {
        userServiceImpl.updateUser(Integer.valueOf(request.getParameter("age")),request.getParameter("name"));
        return "update success";
    }

    @RequestMapping("/updateUser3/{name}/{age}")
    public String updateUserWithPath(@PathVariable String name,@PathVariable int age) {
        userServiceImpl.updateUser(age,name);
        return "update success";
    }
    @PostMapping("/addUser/mybatis")
    public UserBean addUser(@Validated @RequestBody UserBean user) {
        userServiceImpl.addUser(user);
        return user;
    }


}
