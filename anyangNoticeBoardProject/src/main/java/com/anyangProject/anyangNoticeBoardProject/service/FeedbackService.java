package com.anyangProject.anyangNoticeBoardProject.service;

import com.anyangProject.anyangNoticeBoardProject.dto.FeedbackDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedbackService {

    @Autowired
    private JavaMailSender emailSender;
    private final static String adminEmail = "vytndyd99@dankook.ac.kr";

    public void sendSimpleMessage(FeedbackDTO feedbackDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("vytndyd99@gmail.com");
        message.setTo(adminEmail);
        message.setSubject(feedbackDTO.getTitle());
        message.setText(feedbackDTO.getContent());
        emailSender.send(message);

    }
}
