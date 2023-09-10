package com.anyangProject.anyangNoticeBoardProject.controller;

import com.anyangProject.anyangNoticeBoardProject.entitiy.Question;
import com.anyangProject.anyangNoticeBoardProject.entitiy.UserEntity;
import com.anyangProject.anyangNoticeBoardProject.form.AnswerForm;
import com.anyangProject.anyangNoticeBoardProject.form.QuestionForm;
import com.anyangProject.anyangNoticeBoardProject.service.QuestionService;
import com.anyangProject.anyangNoticeBoardProject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Question> paging = this.questionService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "questionForm";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult
    , Principal principal) {
        if(bindingResult.hasErrors()) {
            return "questionForm";
        }
        // TODO 질문 저장하기
        UserEntity userEntity = this.userService.getUser(
                principal.getName()
        );
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(),
                userEntity);
        return "redirect:/question/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String questionModify(QuestionForm questionForm,
                                 @PathVariable("id") Integer id,
                                 Principal principal) {
        Question question = this.questionService.getQuestion(
                id
        );

        if(!question.getAuthor().getUsername().equals(
                principal.getName()
        )) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "수정 권한이 없습니다."
            );
        }

        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "questionForm";

    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String questionModify(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult,
                                 Principal principal,
                                 @PathVariable("id") Integer id) {
        if(bindingResult.hasErrors()) {
            return "questionForm";
        }
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "수정 권한이 없습니다.");
        }
        this.questionService.modify(question, questionForm.getSubject(),
                questionForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        if (!question.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.questionService.delete(question);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        UserEntity userEntity = this.userService.getUser(principal.getName());
        this.questionService.vote(question, userEntity);
        return String.format("redirect:/question/detail/%s", id);
    }
}
