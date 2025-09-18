package com.nishtha.CarbonCredit.service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject("Your Login OTP");
            msg.setText("Your OTP is: " + otp + " (valid for 5 minutes)");
            mailSender.send(msg);
        } catch (Exception e) {
            // For hackathon/demo, log and continue
            System.out.println("Email send simulated: OTP=" + otp + " to " + to);
        }
    }
}

