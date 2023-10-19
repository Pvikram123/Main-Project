package com.mileStone.MainProject.service;

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



    public String emailforsender="srivikram02@gmail.com";

    public String addData(SignUpForm data){
        data.setUserPassword(passwordEncoder.encode(data.getUserPassword()));
        signUpFormRepository.save(data);
        return ("All the data is saved");
    }

    public Object  sendMail(SignUpForm signUpForm) {
        SimpleMailMessage data = new SimpleMailMessage();
        data.setFrom(emailforsender);
        data.setTo(signUpForm.getEmailId());
        data.setSubject("its wprking are not");
        data.setText(String.valueOf(signUpForm));
        javaMailSender.send(data);
        return ("message is send final");
    }
    public String sendingData(SignUpForm signUpForm){
        SignUpForm existinguser= signUpFormRepository.findByEmailIdIgnoreCase(signUpForm.getEmailId());
        if(existinguser!=null){
            return ("the email is exist");
        }
        else {
            signUpForm.setUserPassword(passwordEncoder.encode(signUpForm.getUserPassword()));
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

    public String conformuseraccount(String userdata) {
        CreatingToken token = creatingTokenRepository.findByConfirmationToken(userdata);
        if (token == null) {
            System.out.println(token);

            return ("the id is in valid");
        } else {
            SignUpForm data = signUpFormRepository.findByEmailIdIgnoreCase(token.getSignUpForm().getEmailId());
            data.setEnabled(true);
            signUpFormRepository.save(data);
            return ("successfully Data is added");
        }
    }

}
