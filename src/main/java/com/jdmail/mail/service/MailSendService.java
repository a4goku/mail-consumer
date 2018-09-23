package com.jdmail.mail.service;

import com.jdmail.mail.entity.MailSend;
import com.jdmail.mail.enumeration.MailStatus;
import com.jdmail.mail.enumeration.RedisPriorityQueue;
import com.jdmail.mail.helper.GeneratorMailTemplateHelper;
import com.jdmail.mail.mapper.MailSend1Mapper;
import com.jdmail.mail.mapper.MailSend2Mapper;
import com.jdmail.mail.utils.FastJsonConvertUtil;
import com.jdmail.mail.vo.MailData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MailSendService {

    private static Logger LOGGER = LoggerFactory.getLogger(MailSendService.class);
    @Autowired
    private MailSend1Mapper mailSend1Mapper;

    @Autowired
    private MailSend2Mapper mailSend2Mapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private GeneratorMailTemplateHelper generatorMailTemplateHelper;

    public void sendMessage4Order(MailSend ms){
        try {
            //1数据准备
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("userName", ms.getSendId());
            params.put("createData", DateFormatUtils.format(ms.getUpdateTime(), "yyyy年MM月dd日"));
            params.put("exportUrl", "www.baidu.com");

            MailData data = new MailData();
            data.setTemplateName("SHEET");
            data.setUserId(ms.getSendId());
            data.setFrom("huxiaoyan922@163.com");
            data.setTo(ms.getSendTo());
            data.setSubject("【京东订单】");
            data.setParam(params);

            //2模板渲染和发送
            generatorMailTemplateHelper.generatorAndSend(data);
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
