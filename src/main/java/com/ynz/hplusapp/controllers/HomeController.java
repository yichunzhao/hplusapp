package com.ynz.hplusapp.controllers;

import com.ynz.hplusapp.beans.Login;
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

//    /**
//     * In general, Spring-MVC will always make a call first to that method, before it calls any request handler methods.
//     * That is, @ModelAttribute methods are invoked before the controller methods annotated with @RequestMapping are
//     * invoked. The logic behind the sequence is that, the model object has to be created before any processing starts
//     * inside the controller methods.
//     */
//    @ModelAttribute("newUser")
//    public User getDefaultUser() {
//        return new User();
//    }
//
//    @ModelAttribute("genderItems")
//    public List<String> getGenderItems() {
//        return Arrays.asList("Female", "Male", "Others");
//    }
//
//    @ModelAttribute("login")
//    public Login getLogIn() {
//        return new Login();
//    }

}
