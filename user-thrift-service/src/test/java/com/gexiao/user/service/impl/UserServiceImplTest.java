package com.gexiao.user.service.impl;

import com.gexiao.user.service.UserInfo;
import org.apache.thrift.TException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void getUserById() throws TException {
        UserInfo userById = userService.getUserById(1);
        Assert.assertNotNull(userById);
    }

    @Test
    public void getUserByName() {
    }

    @Test
    public void registryUser() {
    }
}
