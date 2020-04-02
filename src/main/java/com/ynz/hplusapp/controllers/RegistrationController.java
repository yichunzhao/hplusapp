package com.ynz.hplusapp.controllers;

import com.ynz.hplusapp.beans.User;
import com.ynz.hplusapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registerUser")
    public String resisterUser(@ModelAttribute("newUser") User user, Model model) {
        System.out.println("in registration controller");

        userRepository.save(user);

        model.addAttribute("dataSaved", "user registered successfully");

        return "login";
    }
}
