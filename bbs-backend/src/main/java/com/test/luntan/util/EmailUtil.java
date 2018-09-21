package com.test.luntan.util;

import com.test.luntan.model.EmailConnection;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Properties;

@Component
@ConfigurationProperties(prefix = "spring.mail")
public class EmailUtil {

    @Resource
    private EmailConnection emailConnection;

    @Resource
    private  JavaMailSender javaMailSender;

    public static JavaMailSender mailSender;

    public static EmailConnection connection;

    @PostConstruct
    public  void init(){
        mailSender = javaMailSender;
        connection = emailConnection;
    }

    public static void sendFindPasswordEmail(String email,String username){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(connection.getHost());
        sender.setUsername(connection.getUsername());
        sender.setPassword(connection.getPassword());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(connection.getUsername());
        message.setTo(email);
        message.setSubject("主题："+username+"密码找回");
        message.setText("【简途旅行】"+username+"你好，请访问以下链接找回密码：https://localhost:3000/findpassword?"+username);

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        sender.setJavaMailProperties(prop);
        sender.send(message);

    }

}
