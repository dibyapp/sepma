package com.sepmafibre.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String name, String email, String mobile, String body) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo("sepmafibre@gmail.com");
        mailMessage.setSubject("Contact From "+name);
        mailMessage.setText("Mobile : "+mobile+" /n "+body);
        mailMessage.setFrom(email);

        javaMailSender.send(mailMessage);
    }
}