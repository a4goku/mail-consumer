package com.jdmail.mail.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMailTask {
    @Scheduled(initialDelay = 5000, fixedDelay = 2000)
    public void intervalFast(){
        System.out.println("开始执行。。。。。。");
    }
}
