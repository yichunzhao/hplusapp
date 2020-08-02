package com.ynz.hplusapp.controllers;

import com.ynz.hplusapp.beans.Login;
import com.ynz.hplusapp.beans.User;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Arrays;
import java.util.List;

/**
 * Specialization of @Component for classes that declare @ExceptionHandler, @InitBinder, or @ModelAttribute methods
 * to be shared across multiple @Controller classes.
 */
@ControllerAdvice
public class DefaultModelAttributeController {

    /**
     * @ModelAttribute methods are invoked before the controller methods annotated with @RequestMapping are
     * invoked. The logic behind the sequence is that, the model object has to be created before any processing starts
     * inside the controller methods.
     */
    @ModelAttribute("newUser")
    public User getDefaultUser() {
        return new User();
    }

    @ModelAttribute("genderItems")
    public List<String> getGenderItems() {
        return Arrays.asList("Female", "Male", "Others");
    }

    @ModelAttribute("login")
    public Login getLogIn() {
        return new Login();
    }

}
