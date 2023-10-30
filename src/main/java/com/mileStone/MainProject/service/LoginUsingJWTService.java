package com.mileStone.MainProject.service;

import com.mileStone.MainProject.config.JwtConfiguration;
import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.repository.signupformrepository.SignUpFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginUsingJWTService {
    @Autowired
    private SignUpFormRepository signUpFormRepository;
    @Autowired
    private JwtConfiguration jwtConfiguration;

    public Object login2(SignUpForm signUpForm) {
        SignUpForm checkEmail = signUpFormRepository.findByEmailIdIgnoreCase(signUpForm.getEmailId());
        if (checkEmail != null) {
            BCryptPasswordEncoder check = new BCryptPasswordEncoder();
            check.matches(signUpForm.getUserPassword(), checkEmail.getUserPassword());
            HttpHeaders headers=new HttpHeaders();
            headers.set("Authorization",""+jwtConfiguration.creatingJWT(signUpForm));
           return headers;

        }

        return ("user details are not valid");
    }

}
