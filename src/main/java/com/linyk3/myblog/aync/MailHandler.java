package com.linyk3.myblog.aync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linyk3.myblog.util.MailSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MailHandler implements EventHandler{

    @Autowired
    private MailSender mailSender;

    @Override
    public void doHandler(EventModel model) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",model.getExts("name"));
        map.put("message",model.getExts("message"));
        mailSender.sendWithHTMLTemplate(model.getExts("email"),"咨询客户【"+model.getExts("name")+"】联系方式【"+model.getExts("usermail")+"】","mail.html",map);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.COMMENT);
    }
}
