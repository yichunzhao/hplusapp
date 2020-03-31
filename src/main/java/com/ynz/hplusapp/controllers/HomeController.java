package com.ynz.hplusapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String getHome(){
        System.out.println("in home controller");
        return "index";
    }

}
