package com.mileStone.MainProject.controller;

import com.mileStone.MainProject.config.JwtConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsingjwtLoginController {

    @Autowired
    JwtConfiguration jwtConfiguration;

    String Skey="seceretKey";


    @RequestMapping(value = "checking",method = RequestMethod.GET)
    public String getting(@RequestBody String token){
      return ("get");
    }

}
