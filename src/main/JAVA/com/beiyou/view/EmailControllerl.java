package com.beiyou.view;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/email")
public class EmailControllerl {
    @Autowired
    JavaMailSender mailSender;


    @GetMapping("/go")
        public String sendMail () {

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("13674966707@163.com");
//        13674966707@163.com
            mailMessage.setTo("1217433859@qq.com");
            mailMessage.setSubject("好好學習");
            mailMessage.setText("不要用死玩游戲");
            mailSender.send(mailMessage);
            return "false";
        }


}
