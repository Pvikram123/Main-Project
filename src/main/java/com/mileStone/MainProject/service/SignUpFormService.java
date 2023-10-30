package com.mileStone.MainProject.service;

import com.mileStone.MainProject.config.PasswordEncrypt;
import com.mileStone.MainProject.models.CreatingToken;
import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.repository.signupformrepository.CreatingTokenRepository;
import com.mileStone.MainProject.repository.signupformrepository.SignUpFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SignUpFormService {

    @Autowired
    private SignUpFormRepository signUpFormRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private CreatingTokenRepository creatingTokenRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PasswordEncrypt passwordEncrypt;



    public String sendingData(SignUpForm signUpForm){
        SignUpForm existingUser= signUpFormRepository.findByEmailIdIgnoreCase(signUpForm.getEmailId());
        if(existingUser!=null){
            return ("the email is exist");
        }
        else {
            String salt= String.valueOf(passwordEncrypt.passwordEncripted());
            signUpForm.setUserPassword(passwordEncoder.encode(salt+signUpForm.getUserPassword()));
            signUpForm.setSalt(salt);
            signUpFormRepository.save(signUpForm);
            CreatingToken creatingToken=new CreatingToken(signUpForm);
            creatingTokenRepository.save(creatingToken);

            SimpleMailMessage data=new SimpleMailMessage();
            data.setFrom("srivikram02@gmail.com");
            data.setTo(signUpForm.getEmailId());
            data.setSubject("please verify your account");
            data.setText("for conformation please click here :"+"http://localhost:5000/confirm-account?token="+creatingToken.getConfirmationToken()) ;

            emailService.sendEmail(data);

            return ("the link send to your mail");

        }

    }

    public String conformUserAccount(String userdata) {
       CreatingToken token = creatingTokenRepository.findByConfirmationToken(userdata);
        if (token == null) {


            return ("the id is in valid");
        } else {
            SignUpForm data = signUpFormRepository.findByEmailIdIgnoreCase(token.getSignUpForm().getEmailId());
            data.setEnabled(true);
            signUpFormRepository.save(data);
            return ("successfully Data is added");
        }
    }

}
