package com.ynz.hplusapp.controllers;


import com.ynz.hplusapp.beans.Login;
import com.ynz.hplusapp.beans.User;
import com.ynz.hplusapp.exceptions.ApplicationException;
import com.ynz.hplusapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("login")//in class level saving the login data model into the session
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    //@ModelAndAttribute Login login bean has been declared in the ControllerAdvice class, DefaultModelAttributeController.

    @PostMapping("/login")
    public String login(@ModelAttribute("login") Login login, Model model, HttpSession session) {

        User user = userRepository.findByUserName(login.getUserName());

        if (user == null) throw new ApplicationException("User is not found.");

        session.setMaxInactiveInterval(600);

        return "forward:/userProfile";
    }

    /**
     * by default specific controller exception will be picked up, as having a global exception handler.
     */

//    @ExceptionHandler(ApplicationException.class)
//    public String handleException() {
//        System.out.println("in exception handler of login controller.");
//        return "error";
//    }
}
