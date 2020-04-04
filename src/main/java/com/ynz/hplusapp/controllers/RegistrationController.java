package com.ynz.hplusapp.controllers;

import com.ynz.hplusapp.beans.User;
import com.ynz.hplusapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class, "dateOfBirth",
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
    }

    /**
     * When used as a method argument, it indicates the argument should be retrieved from the model. When not present,
     * it should be first instantiated and then added to the model and once present in the model, the arguments fields
     * should be populated from all request parameters that have matching names.
     */
    @PostMapping("/registerUser")
    public String resisterUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model) {
        //@ModelAttribute defines data binding, mapping form inputs to java data model.
        //Model defines Model mapping, from java data model to view fields.
        System.out.println("in registration controller");
        System.out.println(user.getDateOfBirth());

        //if mapping having error.
        if(result.hasErrors()){
            return "register";
        }

        userRepository.save(user);

        model.addAttribute("dataSaved", "user registered successfully");

        return "login";
    }
}
