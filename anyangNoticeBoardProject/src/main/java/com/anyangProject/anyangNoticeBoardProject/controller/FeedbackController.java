package com.anyangProject.anyangNoticeBoardProject.controller;

import com.anyangProject.anyangNoticeBoardProject.entitiy.Feedback;
import com.anyangProject.anyangNoticeBoardProject.service.FeedbackService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String feedbackPage(Model model,
                               Feedback feedback) {
        model.addAttribute("feedback", feedback);
        return "feedbackPage";
    }

    @PostMapping("/feedback")
    public String feedbackMail(Feedback feedback)
        throws MessagingException, IOException {
        feedbackService.sendSimpleMessage(feedback);
        return "feedbackPage";
    }
}
