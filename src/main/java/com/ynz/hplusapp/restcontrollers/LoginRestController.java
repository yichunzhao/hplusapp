package com.ynz.hplusapp.restcontrollers;

import com.ynz.hplusapp.beans.Login;
import com.ynz.hplusapp.beans.User;
import com.ynz.hplusapp.exceptions.LoginFailureException;
import com.ynz.hplusapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/hplus/rest/loginuser")
    public ResponseEntity<User> loginUser(@RequestBody Login login) {
        System.out.println("username :" + login.getUserName() + " password: " + login.getPassword());

        User user = userRepository.findByUserName(login.getUserName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if (user.getUserName().equals(login.getUserName()) && user.getPassword().equals(login.getPassword())) {
            return new ResponseEntity("Welcome" + user.getUserName(), HttpStatus.OK);
        } else {
            throw new LoginFailureException("Invalid userName or password");
        }

    }
}
