package com.anyangProject.anyangNoticeBoardProject.controller;

import com.anyangProject.anyangNoticeBoardProject.dto.FeedbackDTO;
import com.anyangProject.anyangNoticeBoardProject.service.FeedbackService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/develop")
@Slf4j
public class FeedbackController {

    private final FeedbackService feedbackService;


    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }
    @GetMapping("/feedback")
    public String feedbackPage() {
        return "feedbackPage";
    }

    @PostMapping("/feedback")
    public String feedbackMail(FeedbackDTO feedbackDTO)
        throws MessagingException, IOException {
        feedbackService.sendSimpleMessage(feedbackDTO);
        return "feedbackPage";
    }
}
