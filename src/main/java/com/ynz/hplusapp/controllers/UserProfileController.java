package com.ynz.hplusapp.controllers;

import com.ynz.hplusapp.beans.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.management.modelmbean.ModelMBeanOperationInfo;

@Controller
public class UserProfileController {

    @PostMapping("/userProfile")
    public String getUserProfile(@SessionAttribute("login")Login login, Model model){
        System.out.println("in user profile controller.");
        System.out.println("userName from login session object: "+ login.getUserName() );

        model.addAttribute("userName",login.getUserName());

        return "profile";
    }
}
