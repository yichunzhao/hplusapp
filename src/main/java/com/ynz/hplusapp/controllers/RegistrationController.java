package com.ynz.hplusapp.controllers;

import com.ynz.hplusapp.beans.User;
import com.ynz.hplusapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/registerUser")
    public String resisterUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model) {
        //@ModelAttribute defines data binding, mapping form inputs to java data model.
        //Model defines Model mapping, from java data model to view fields.
        System.out.println("in registration controller");

        //if mapping having error.
        if(result.hasErrors()){
            return "register";
        }

        userRepository.save(user);

        model.addAttribute("dataSaved", "user registered successfully");

        return "login";
    }
}
