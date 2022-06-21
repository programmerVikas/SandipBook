package com.sandip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DirectorController {

    // default login page redirection
    @RequestMapping("/")
    public String goToLogin(@ModelAttribute("signUpSuccess") String signUpSuccess, Model model) {
        model.addAttribute("signUpSuccess", signUpSuccess);
        System.out.println("succes register : "+signUpSuccess);
        return "login";
    }

    // going on registration page.........
    @RequestMapping("/register")
    public String goToRegister(Model model) {
        return "register";
    }



    

}