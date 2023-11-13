package com.mileStone.MainProject.controller.user;


import com.mileStone.MainProject.config.JwtConfigurationForUser;
import com.mileStone.MainProject.dtos.userdtos.UserNameDTOs;
import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.service.userservice.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtConfigurationForUser jwtConfigurationForUser;

    private final String Name="secretKey";

    @RequestMapping(value = "/postDataUser", method = RequestMethod.POST)
    public Object postData(@RequestBody User data) {
        return userService.data(data);
    }

    @RequestMapping(value = "/getDataUser", method = RequestMethod.GET)
    public List<Object> delete(@RequestParam("token") String token, @RequestHeader(value = "Authorization") String string) throws Exception {
        try {
            jwtConfigurationForUser.verify(string);
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(Name)
                    .parseClaimsJws(string);
            Claims claims = claimsJws.getBody();
            String role = (String) claims.get("role");
            if (role.equals("ADMIN")){
                return userService.getUsernames(token);

            }
            else {
                return Collections.singletonList(("you don't have access"));
            }

        } catch (SignatureException e) {
            // Handle invalid signature
            throw new Exception("Invalid token signature");
        } catch (Exception e) {
            throw new Exception("verify your token " );
        }
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public Object login(@RequestBody User data) {
        return userService.login(data);
    }

    @RequestMapping(value = "/friendRequestUser", method = RequestMethod.POST)
    public Object friendRequest(@RequestParam("Token") String data, @RequestBody UserNameDTOs UserNameDTOs) {
        return userService.friendRequest(data, UserNameDTOs);
    }

    @RequestMapping(value = "/gettingRequestUser", method = RequestMethod.GET)
    public Object getting(@RequestParam("token") String data) {
        return userService.friendRequestGetting(data);
    }

    @RequestMapping(value = "/acceptUser", method = RequestMethod.POST)
    public Object accept(@RequestBody UserNameDTOs UserNameDTOs, @RequestParam("token") String token) {
        return userService.acceptFriendRequest(UserNameDTOs, token);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String delete(@RequestBody UserNameDTOs UserNameDTOs, @RequestParam("token") String token) {
        return userService.rejectFriendRequest(UserNameDTOs, token);
    }

}