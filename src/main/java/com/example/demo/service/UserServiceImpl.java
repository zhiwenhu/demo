package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserBean;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public List<UserBean> getAllUser() {
        log.info("get all users");
        return userDao.getUser();

    }

    @CachePut(value = "myredis", key = "#name")
    @Override
    public UserBean updateUser(int age, String name) {
        userDao.updateUser(age, name);
        log.info("update user " + name);
        return userDao.getOneUser(name);
    }

    @Override
    @Cacheable(value = "myredis", key = "#name" ,unless="#result == null")
    public UserBean getOneUser(String name) {
        log.info("get user " + name);
        return userDao.getOneUser(name);
    }

    @CachePut(value = "myredis", key = "#user.name")
    @Override
    public UserBean addUser(UserBean user) {
        userDao.addUser(user);
        log.info("add user " + user.getName());
        return user;
    }
}
