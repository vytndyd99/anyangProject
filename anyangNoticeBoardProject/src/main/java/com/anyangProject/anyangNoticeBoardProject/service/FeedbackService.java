package com.anyangProject.anyangNoticeBoardProject.service;

import com.anyangProject.anyangNoticeBoardProject.entitiy.Feedback;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedbackService {

    @Autowired
    private JavaMailSender emailSender;
    private final static String toEmail = "vytndyd99@gmail.com";

    public void sendSimpleMessage(Feedback feedbackDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("vytndyd99@gmail.com");
        message.setTo(toEmail);;
        message.setSubject(feedbackDTO.getTitle());
        message.setText(feedbackDTO.getContent());
        emailSender.send(message);

    }
}
