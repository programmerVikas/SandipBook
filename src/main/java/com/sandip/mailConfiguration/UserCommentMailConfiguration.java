package com.sandip.mailConfiguration;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class UserCommentMailConfiguration {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendCommentMail(String message, String subject, String to, String userCommentEmail, String userCommentName, String post) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("<p>"+post+"</p><p><br></p>"+userCommentName+" Commented on your post <p><br></p> <br><b>" + message
                +"</b><p><br></p>You can reply to the user mail : <b>"+userCommentEmail+"</b></br><p><br></p>Regards<br>SandipBook Team", true);
        // helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        javaMailSender.send(msg);

    }

}

// Otp sender