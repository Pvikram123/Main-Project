package com.mileStone.MainProject.controller;

import com.mileStone.MainProject.service.mongodbservice.FriendListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendListController {
    @Autowired
    FriendListService friendListService;

    @RequestMapping(value = "/FriendList",method = RequestMethod.GET)
    public Object data(@RequestParam("token") String token){
     return    friendListService.friendList(token);
    }
}
