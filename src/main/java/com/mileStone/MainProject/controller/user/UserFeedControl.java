package com.mileStone.MainProject.controller.user;


import com.mileStone.MainProject.models.usermodel.UserFeed;
import com.mileStone.MainProject.service.userservice.UserFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
public class UserFeedControl {
    @Autowired
    UserFeedService userFeedService;

    @RequestMapping(value = "/postUserFeed", method = RequestMethod.POST)
    public String data(@RequestParam("file") MultipartFile file,@RequestParam("title") String title,@RequestParam("description") String description
            ,@RequestParam("category") String category,@RequestParam("visibility") String visibility,@RequestParam("tags") String tags,@RequestParam("token")String token) throws IOException {
        return userFeedService.saveData(file,title,description,category,visibility,tags,token);
    }

    @RequestMapping(value = "/getAllDataUserFeed", method = RequestMethod.GET)
    public List<UserFeed> getAllInfo() {
        return userFeedService.gettingAllData();
    }


}
