package com.jdmail.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailConsumerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private JavaMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("wangyi_062500@163.com");
        message.setTo("wangyi_062500@hotmail.com");
        message.setSubject("主题：这是一封用代码写的测试邮件"+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        message.setText("Test content");

        mailSender.send(message);
    }



}
