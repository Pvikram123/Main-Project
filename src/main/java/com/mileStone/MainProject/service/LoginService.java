package com.mileStone.MainProject.service;

import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.repository.signupformrepository.SignUpFormRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class LoginService {
@Autowired
private SignUpFormRepository signUpFormRepository;
    private HttpServletResponse response;

    public String login2(SignUpForm signUpForm){
        SignUpForm checkEmail=signUpFormRepository.findByEmailIdIgnoreCase(signUpForm.getEmailId());
        if(checkEmail !=null) {
            BCryptPasswordEncoder check = new BCryptPasswordEncoder();
            check.matches(signUpForm.getUserPassword(), checkEmail.getUserPassword());
                return ("user register");
        }
        else {
            return ("user details are not valid");
        }

    }

}
