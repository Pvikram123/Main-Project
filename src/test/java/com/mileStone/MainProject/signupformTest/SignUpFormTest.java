package com.mileStone.MainProject.signupformTest;

import com.mileStone.MainProject.config.PasswordEncrypt;
import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.repository.signupformrepository.CreatingTokenRepository;
import com.mileStone.MainProject.repository.signupformrepository.SignUpFormRepository;
import com.mileStone.MainProject.service.EmailService;
import com.mileStone.MainProject.service.SignUpFormService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class SignUpFormTest {

    @Mock
    SignUpFormRepository signUpFormRepository;
    @Mock
    PasswordEncrypt passwordEncrypt;
    @Mock
    CreatingTokenRepository creatingTokenRepository;
    @Mock
    EmailService emailService;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    SignUpFormService signUpFormService;

    @Test
    void sendingData_InSignUpForm(){
        SignUpForm data =new SignUpForm();
        data.setUserFirstName("john");data.setUserLastName("dummy");data.setUserGender("male");data.setUserPassword("ravi22");data.setUserReEntryPassword("ravi22");data.setUserDateOfBirth("24/01/2002");data.setEmailId("srivikram002@gmail.com");data.setUserID(3);

        when(signUpFormRepository.findByEmailIdIgnoreCase(data.getEmailId())).thenReturn(null);

        String result=signUpFormService.sendingData(data);
        assertEquals("the link send to your mail",result);
    }
    @Test
    void sendingData_InSignUpForm_emailAllReadyExit(){
        SignUpForm data =new SignUpForm();
        data.setUserFirstName("john");data.setUserLastName("dummy");data.setUserGender("male");data.setUserPassword("ravi22");data.setUserReEntryPassword("ravi22");data.setUserDateOfBirth("24/01/2002");data.setEmailId("srivikram002@gmail.com");data.setUserID(3);

        when(signUpFormRepository.findByEmailIdIgnoreCase(data.getEmailId())).thenReturn(data);

        String result=signUpFormService.sendingData(data);
        assertEquals("the email is exist",result);
    }
}
