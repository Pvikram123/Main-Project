package com.mileStone.MainProject.controller;

import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.service.EmailService;
import com.mileStone.MainProject.service.SignUpFormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class SignUpFormController {


    @Autowired
    private SignUpFormService signUpFormService;


@Autowired
private EmailService emailService;

   @RequestMapping(value = "/add",method = RequestMethod.GET)
    private String sendmailToUser(@RequestBody SignUpForm gettingData){
        return signUpFormService.sendMail(gettingData).toString();
   }
    @RequestMapping(value = "/reister",method = RequestMethod.POST)
    public String sendingData(@RequestBody SignUpForm signUpForm){
    return signUpFormService.sendingData(signUpForm);

  }
  @RequestMapping(value = "/confirm-account",method = {RequestMethod.GET,RequestMethod.POST})
    public String ConformUserAccount(@RequestParam("token") String userdata){
       return signUpFormService.conformuseraccount(userdata);

    }


}
