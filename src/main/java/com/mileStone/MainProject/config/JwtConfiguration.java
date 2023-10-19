package com.mileStone.MainProject.config;

import com.mileStone.MainProject.models.SignUpForm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@Component
public class JwtConfiguration {
    private static String Key = "seceretKey";
    private long expire = 5 * 60;


    @SneakyThrows
    public String creatingJWT(SignUpForm signUpForm) {
        try {
            long current = System.currentTimeMillis();
            long expireDate = current + expire * 1000;
            Date now = new Date(current);
            Date exp = new Date(expireDate);
            Claims claims = Jwts.claims()
                    .setIssuer(String.valueOf((signUpForm.getUserID())))
                    .setIssuedAt(now)
                    .setExpiration(exp);
            claims.put("emailId", signUpForm.getEmailId());
            return Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256, Key)
                    .compact();
        } catch (Exception e) {
            throw new AccessDeniedException("Access Denied");
        }

    }
    public Claims verify(String authorization) throws Exception {

        try {
          Claims claims=  Jwts.parser().setSigningKey(Key).parseClaimsJws(authorization).getBody();
            return claims;
        } catch (Exception e) {
            throw new AccessDeniedException("Access Denied");
        }

    }
}
