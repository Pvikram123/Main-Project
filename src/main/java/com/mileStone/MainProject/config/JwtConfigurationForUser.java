package com.mileStone.MainProject.config;

import com.mileStone.MainProject.models.SignUpForm;
import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.Date;
@Component
public class JwtConfigurationForUser {

    @Autowired
    UserRepository userRepository;

        private static final String secretKey= "secretKey";
        private long expire = 5 * 60;


        @SneakyThrows
        public String creatingJWT(User user) {
            try {
                User data=userRepository.findByUserNameData(user.getUserNameData());
                long current = System.currentTimeMillis();
                long expireDate = current + expire * 1000;
                Date now = new Date(current);
                Date exp = new Date(expireDate);
                Claims claims = Jwts.claims()
                        .setIssuer(String.valueOf((user.getUserNameData())))
                        .setIssuer(String.valueOf(user.getRole()))
                        .setIssuedAt(now)
                        .setExpiration(exp);
                claims.put("userNameData", user.getUserNameData());
                claims.put("role",data.getRole());
                return Jwts.builder()
                        .setClaims(claims)
                        .signWith(SignatureAlgorithm.HS256, secretKey)
                        .compact();
            } catch (Exception e) {
                throw new AccessDeniedException("Access Denied");
            }

        }
        public Claims verify(String authorization) throws Exception {

            try {
                Claims claims=  Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authorization).getBody();
                return claims;
            } catch (Exception e) {
                throw new AccessDeniedException("Access Denied");
            }

        }
    }

