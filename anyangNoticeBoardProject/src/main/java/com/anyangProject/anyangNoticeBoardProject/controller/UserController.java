package com.anyangProject.anyangNoticeBoardProject.controller;

import com.anyangProject.anyangNoticeBoardProject.entitiy.UserCreateForm;
import com.anyangProject.anyangNoticeBoardProject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/signUp")
    public String signUp(UserCreateForm userCreateForm) {
        return "signUp_form";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "signUp_form";
        }

        if(!userCreateForm.getPassword().equals(userCreateForm.getPasswordCheck())) {
            bindingResult.rejectValue("passwordCheck", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signUp_form";
        }

       try {
           userService.create(userCreateForm.getUsername(),
                   userCreateForm.getEmail(), userCreateForm.getPassword());
       } catch (DataIntegrityViolationException e) {
           e.printStackTrace();
           bindingResult.reject("signUpFailed",
                   "이미 등록된 사용자입니다.");
           return "signUp_form";
       } catch (Exception e) {
           e.printStackTrace();
           bindingResult.reject("signUpFailed"
           , e.getMessage());
           return "signUp_form";
       }
        return "redirect:/";
    }

    @GetMapping("/login")
        public String login() {
        return "login_form";
    }
}
