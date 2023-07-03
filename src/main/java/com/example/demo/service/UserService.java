package com.example.demo.service;

import com.example.demo.entity.UserBean;
import java.util.List;

public interface UserService {
    List getAllUser();
    void updateUser(int age, String name);
    UserBean getOneUser(String name);
    UserBean addUser(UserBean user);
}
