package com.mileStone.MainProject.controller;

import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginservice;

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String login(@RequestBody SignUpForm signUpForm){
        return  loginservice.login2(signUpForm);


    }
}
