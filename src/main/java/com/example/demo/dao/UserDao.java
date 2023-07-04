package com.example.demo.dao;

import com.example.demo.entity.UserBean;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    List<UserBean> getUser();
    void updateUser(@Param("age") int age,@Param("name") String name);

    UserBean getOneUser(@Param("name")String name);

    int addUser(UserBean user);
}
