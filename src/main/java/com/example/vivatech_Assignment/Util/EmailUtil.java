package com.example.vivatech_Assignment.Util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;


    public void sendOtpToEmail(String email, String otp) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP"+" "+otp);
        mimeMessageHelper.setText("""
        <div>
          <p href="http://localhost:8080/verify-account?email=%s&otp=%s" target="_blank">click link to verify</p>
        </div>
        """.formatted(email, otp), true);

        mailSender.send(mimeMessage);
    }
}
