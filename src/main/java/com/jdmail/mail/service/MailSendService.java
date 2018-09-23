package com.jdmail.mail.service;

import com.jdmail.mail.entity.MailSend;
import com.jdmail.mail.enumeration.MailStatus;
import com.jdmail.mail.enumeration.RedisPriorityQueue;
import com.jdmail.mail.mapper.MailSend1Mapper;
import com.jdmail.mail.mapper.MailSend2Mapper;
import com.jdmail.mail.utils.FastJsonConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {

    private static Logger LOGGER = LoggerFactory.getLogger(MailSendService.class);
    @Autowired
    private MailSend1Mapper mailSend1Mapper;

    @Autowired
    private MailSend2Mapper mailSend2Mapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


}
