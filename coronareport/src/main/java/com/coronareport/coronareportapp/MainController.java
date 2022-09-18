package com.coronareport.coronareportapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String root(Model model){
        model.addAttribute("test1","Hey let's begin");
        return "mainTemplate";
    }
}
