package com.jdmail.mail.task;

import com.jdmail.mail.entity.MailSend;
import com.jdmail.mail.enumeration.RedisPriorityQueue;
import com.jdmail.mail.service.MailSendService;
import com.jdmail.mail.utils.FastJsonConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMailTask {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @Autowired
    private MailSendService mailSendService;

    @Scheduled(initialDelay = 5000, fixedDelay = 2000)
    public void intervalFast(){

        System.out.println("fast开始执行。。。。。。");
        ListOperations<String, String> opsForList = redisTemplate.opsForList();

        String ret = opsForList.leftPop(RedisPriorityQueue.FAST_QUEUE.getCode());

        if(!StringUtils.isBlank(ret)){
            System.err.println(ret);
            MailSend ms = FastJsonConvertUtil.convertJSONToObject(ret, MailSend.class);

            mailSendService.sendMessage4Order(ms);
        }

    }
//
//    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
//    public void intervalNormal(){
//        System.out.println("normal开始执行。。。。。。");
//    }
//
//    @Scheduled(initialDelay = 5000, fixedDelay = 60000)
//    public void intervalDefer(){
//        System.out.println("defer开始执行。。。。。。");
//    }
}
