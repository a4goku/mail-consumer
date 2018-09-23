package com.jdmail.mail.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMailTask {
    @Scheduled(initialDelay = 5000, fixedDelay = 2000)
    public void intervalFast(){
        System.out.println("fast开始执行。。。。。。");
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 10000)
    public void intervalNormal(){
        System.out.println("normal开始执行。。。。。。");
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 60000)
    public void intervalDefer(){
        System.out.println("defer开始执行。。。。。。");
    }
}
