package com.lizj.mars.mybatis.service;

import com.lizj.mars.mybatis.bean.User;
import com.lizj.mars.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;


@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public void getUser() {
        User user = userMapper.getId(1);
        System.out.println(user);
    }

    @Transactional
    public void insertUser() {
        User user = new User();
        user.setName("yyy");
        user.setAge(1);
        userMapper.insert(user);
    }
}
