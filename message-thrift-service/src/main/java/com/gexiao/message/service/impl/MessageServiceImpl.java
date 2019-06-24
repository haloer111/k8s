package com.gexiao.message.service.impl;

import com.gexiao.message.MessageService;
import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService.Iface {
    @Override
    public boolean sendMobileMessage(String mobile, String message) throws TException {
        return true;
    }

    @Override
    public boolean sendEmailMessage(String email, String message) throws TException {
        return true;
    }
}
