package com.sandip.mailConfiguration;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailConfiguration {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendOTPMail(String message, String subject, String to) throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText("Dear User,<p><br></p> Please enter given <b>OTP</b> to verify your account : <b>" + message
                + "</b><p><br></p>Regards<br>SandipBook Team", true);
        // helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
        javaMailSender.send(msg);

    }

}
