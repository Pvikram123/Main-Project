package com.mileStone.MainProject.service;

import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.repository.signupformrepository.CreatingTokenRepository;
import com.mileStone.MainProject.repository.signupformrepository.SignUpFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ForgotPasswordService {

    @Autowired
  private   SignUpFormRepository signUpFormRepository;
    @Autowired
   private EmailService emailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
   private CreatingTokenRepository creatingTokenRepository;
   public String name="srivikram02@gmil.com";
    public String forgotPassword(SignUpForm signUpForm){
      SignUpForm checkEmail=signUpFormRepository.findByEmailIdIgnoreCase(signUpForm.getEmailId());
      if(checkEmail!=null){
          SimpleMailMessage data=new SimpleMailMessage();

          data.setTo(checkEmail.getEmailId());
          data.setFrom(name);
          data.setSubject("Reset your password");
          data.setText("click here to reset your Password "+"http://localhost:5000/resetPassword");
          emailService.sendEmail(data);
          return ("email is send to your register mail");
      }
      else {
          return ("Your email id is in valid");
      }
    }
    public String UpdatePassword(SignUpForm data){
       SignUpForm checkemail=signUpFormRepository.findByEmailIdIgnoreCase(data.getEmailId());
        SignUpForm reEnter = checkemail;
        reEnter.setUserPassword(passwordEncoder.encode(data.getUserPassword()));
        signUpFormRepository.save(reEnter);
        return ("new password is updated");
    }
}
