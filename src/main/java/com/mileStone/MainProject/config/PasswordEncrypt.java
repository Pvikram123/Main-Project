package com.mileStone.MainProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncrypt {
    @Bean
    public PasswordEncoder passwordencripted(){
      return new BCryptPasswordEncoder();
    }



}
