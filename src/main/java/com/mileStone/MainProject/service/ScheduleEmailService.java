package com.mileStone.MainProject.service;

import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleEmailService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

private String email="srivikram02@gmail.com";
//    @Scheduled(fixedDelay = 300000)
    public Object demo(){

      List<User> find= userRepository.findAll();
      List<String> Data1= find.stream().map(m->m.getEmailId()).toList();
            SimpleMailMessage data2 = new SimpleMailMessage();
            data2.setFrom(email);
            data2.setTo(Data1.toArray(new String[0]));
            data2.setSubject("i send email for all the cron");
            data2.setText("using cron I send the email for all  ");
            emailService.sendEmail(data2);
       return ("true");

    }
    public String del(){
        userRepository.deleteAll();
        return ("its done");
    }


}
