package com.ynz.hplusapp.controllers;


import com.ynz.hplusapp.beans.Login;
import com.ynz.hplusapp.beans.User;
import com.ynz.hplusapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login, Model model){

        User user = userRepository.findByUserName(login.getUserName());

        return "search";

    }
}
