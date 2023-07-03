package com.example.demo.controls;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.UserBean;
import com.example.demo.enums.Gender;
import com.example.demo.service.UserServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyControl {

    @Autowired
    UserBean myUser;
    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("/user")
    public String getUser() {
        myUser.setName("Mary");
        myUser.setGender(Gender.FEMALE);
        myUser.setAge(18);

        return myUser.toString();
    }

    @GetMapping("/allUser")
    public String getAllUser() {
        List users = userServiceImpl.getAllUser();
        return JSONObject.toJSONString (users);
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
    @PostMapping("/addUser")
    public UserBean addUser(@Validated @RequestBody UserBean user) {
        userServiceImpl.addUser(user);
        return user;
    }


}
