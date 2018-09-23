package com.jdmail.mail.helper;

import com.jdmail.mail.vo.MailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
public class GeneratorMailTemplateHelper {

    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private TemplateEngine templateEngine;

    public void generatorAndSend(MailData data) throws Exception{
        //模板引擎的全局变量
        Context context = new Context();
        context.setLocale(Locale.CHINA);
        context.setVariables(data.getParam());
        String templateLocation = data.getTemplateName();
        String content = templateEngine.process(templateLocation, context);

        data.setContent(content);
        //调用方法发送
        send(data);
    }

    private void send(MailData data) throws Exception{
        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper mimeHelper = new MimeMessageHelper(mime, true, "UTF-8");

        mimeHelper.setFrom(data.getFrom());
        mimeHelper.setTo(data.getTo());
        mimeHelper.setSubject(data.getSubject());
        mimeHelper.setText(data.getContent(), true);

        mailSender.send(mime);
    }
}
