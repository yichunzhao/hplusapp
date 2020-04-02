package com.ynz.hplusapp.controllers;

import com.ynz.hplusapp.beans.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHome() {
        System.out.println("in home controller");
        return "index";
    }

    @GetMapping("/goToSearch")
    public String goToSearch() {
        System.out.println("in home controller, mapping to search");
        return "search";
    }

    @GetMapping("/goToLogin")
    public String goToLogin() {
        System.out.println("in home controller, mapping to login");
        return "login";
    }

    @GetMapping("/goToRegister")
    public String goToRegister() {
        System.out.println("in home controller, mapping to register");
        return "register";
    }

    @ModelAttribute("newUser")
    public User getDefaultUser(){
        return new User();
    }

    @ModelAttribute("genderItems")
    public List<String> getGenderItems(){
        return Arrays.asList("Female", "Male","Others");
    }

}
