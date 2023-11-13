package com.mileStone.MainProject.controller;

import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordController {
    @Autowired
    private ForgotPasswordService forgotPasswordService;
    @RequestMapping(value = "/sendmail",method = RequestMethod.POST)
    public String sendmail(@RequestBody SignUpForm data){
        return forgotPasswordService.forgotPassword(data);
    }
    @RequestMapping(value = "/resetPassword",method = RequestMethod.PUT)
    public String update(@RequestBody SignUpForm data){
        return forgotPasswordService.UpdatePassword(data);
    }

}
