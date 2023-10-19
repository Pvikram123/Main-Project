package com.mileStone.MainProject.demo;
import com.mileStone.MainProject.config.JwtConfiguration;
import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.repository.signupformrepository.SignUpFormRepository;
import com.mileStone.MainProject.service.LoginUsingJWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class Sending {
    @Autowired
    JwtConfiguration jwtConfiguration;
    @Autowired
    LoginUsingJWTService loginUsingJWTService;

    @Autowired
    SignUpFormRepository signUpFormRepository;
    @GetMapping(value = "token")
    public Object demo(@RequestBody SignUpForm data) throws Exception {
      return loginUsingJWTService.login2(data);
    }
    @GetMapping(value = "testing123")
    public Object getting(@RequestHeader(value = "Authorization") String string )throws Exception{
try {

    jwtConfiguration.verify(string);
   return signUpFormRepository.findAll();


}catch (Exception d){
    return ("its not configure");
      }

    }

}
