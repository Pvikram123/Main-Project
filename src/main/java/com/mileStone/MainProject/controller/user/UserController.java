package com.mileStone.MainProject.controller.user;


import com.mileStone.MainProject.dtos.userdtos.UserNameDTOs;
import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/postDataUser",method = RequestMethod.POST)
    public Object postData(@RequestBody User data){
       return userService.data(data);
    }
    @RequestMapping(value = "/getDataUser",method = RequestMethod.GET)
    public List<String> getDataEntry(){
        return  userService.getUsernames();
    }
    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    public String login(@RequestBody User data){
        return userService.login(data);
    }
    @RequestMapping(value="/friendRequestUser",method = RequestMethod.POST)
    public Object friendRequest(@RequestParam("Token") String data, @RequestBody UserNameDTOs UserNameDTOs){
        return userService.friendRequest(data, UserNameDTOs);
    }
    @RequestMapping(value = "/gettingRequestUser",method = RequestMethod.GET)
    public  Object getting(@RequestParam("Token")String data){
        return userService.friendRequestGetting(data);
    }
    @RequestMapping(value = "/acceptUser",method = RequestMethod.POST)
    public String accept(@RequestBody UserNameDTOs UserNameDTOs){
        return userService.acceptFriendRequest(UserNameDTOs);
    }
    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public String delete(@RequestBody UserNameDTOs UserNameDTOs){
        return userService.rejectFriendRequest(UserNameDTOs);
    }

}
