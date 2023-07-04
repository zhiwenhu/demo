package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public List<UserBean> getAllUser() {
        return userDao.getUser();
    }

    @Override
    public void updateUser(int age, String name) {
        userDao.updateUser(age, name);
    }

    @Override
    public UserBean getOneUser(String name) {
        return userDao.getOneUser(name);
    }

    @Override
    public UserBean addUser(UserBean user) {
        userDao.addUser(user);
        return user;
    }
}
