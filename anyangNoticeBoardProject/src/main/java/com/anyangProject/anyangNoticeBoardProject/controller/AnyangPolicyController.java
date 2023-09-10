package com.anyangProject.anyangNoticeBoardProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anyang")
public class AnyangPolicyController {

    @GetMapping("/policy")
    public String anyangPolicy() {
        return "anyangPolicy";
    }
}
