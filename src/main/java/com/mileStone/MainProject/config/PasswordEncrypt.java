package com.mileStone.MainProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class PasswordEncrypt {
    @Bean
    public PasswordEncoder passwordEncripted(){
      return new BCryptPasswordEncoder();
    }



        public static String generateRandomSalt() {
            SecureRandom random = new SecureRandom();
            byte[] saltBytes = new byte[16];

            random.nextBytes(saltBytes);
            return Base64.getEncoder().encodeToString(saltBytes);
        }
    }


