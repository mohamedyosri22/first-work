package com.spring.mail;

import com.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailSenderService {


    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String proxyEmail;

    @Value("${company.email}")
    private String companyEmail;

    public void sendEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(proxyEmail); // البروكسي
        message.setTo(companyEmail); // بتاع الشركه
        message.setSubject("new email from the website");

        message.setText(user.getFirstName()+" "+user.getLastName()+" sent a new email\n"+
                "\nemail-subject : "+user.getSubject()+"\n============================\n"+
                "\nemail-body : "   +user.getBody()+"\n============================\n"+
                "\ncontact-email : "+user.getEmail()+"\n============================\n"+
                "\ncontact-phone : "+user.getPhone());

        message.setSentDate(new Date());

        mailSender.send(message);

        System.out.println("sent success !!");
    }
}
