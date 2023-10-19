package com.mileStone.MainProject.controller;


import com.mileStone.MainProject.dtos.UserNameDTOs;
import com.mileStone.MainProject.models.User;
import com.mileStone.MainProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/postData",method = RequestMethod.POST)
    public Object postData(@RequestBody User data){
       return userService.data(data);
    }
    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    public List<String> getDataEntry(){
        return  userService.getUsernames();
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestBody User data){
        return userService.login(data);
    }
    @RequestMapping(value="/friendRequest",method = RequestMethod.POST)
    public Object friendRequest(@RequestParam("Token") String data, @RequestBody UserNameDTOs UserNameDTOs){
        return userService.friendRequest(data, UserNameDTOs);
    }
    @RequestMapping(value = "/gettingRequest",method = RequestMethod.GET)
    public  Object getting(@RequestParam("Token")String data){
        return userService.friendRequestGetting(data);
    }
    @RequestMapping(value = "/accept",method = RequestMethod.POST)
    public String accept(@RequestBody UserNameDTOs UserNameDTOs){
        return userService.acceptFriendRequest(UserNameDTOs);
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public String delete(@RequestBody UserNameDTOs UserNameDTOs){
        return userService.rejectFriendRequest(UserNameDTOs);
    }

}
