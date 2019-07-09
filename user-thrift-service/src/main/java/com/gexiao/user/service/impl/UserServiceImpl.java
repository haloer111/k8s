package com.gexiao.user.service.impl;

import com.gexiao.user.mapper.UserMapper;
import com.gexiao.user.service.UserInfo;
import com.gexiao.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService.Iface {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserInfo getUserById(int id) throws TException {
        return userMapper.getUserById(id);
    }

    @Override
    public UserInfo getTeacherById(int id) throws TException {
        return userMapper.getTeacherById(id);
    }

    @Override
    public UserInfo getUserByName(String name) throws TException {
        return userMapper.getUserByName(name);
    }

    @Override
    public void registryUser(UserInfo userInfo) throws TException {
        log.info("传入参数：{}", userInfo);
        int i = userMapper.registerUser(userInfo);
        System.out.println("i===" + i);
    }
}
