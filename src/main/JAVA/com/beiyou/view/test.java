package com.beiyou.view;
import com.beiyou.business.rabbit.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author fly
 */
@RestController
@RequestMapping("/message")
public class test {
    @Autowired
    private Producer producer;
    @GetMapping("/producer")
    public String messageProducer(){
        producer.setMessageForQueue("mq_exchange","mq_url","中国");
        return "发送成功";
    }
    @GetMapping("/login")
    public void login() throws InterruptedException {
        producer.testEmail();
        producer.testVerification();
        producer.testPhone();
    }





}