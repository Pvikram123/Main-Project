package com.mileStone.MainProject.controller.user;



import com.mileStone.MainProject.service.userservice.FeedGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedGetController {
    @Autowired
    FeedGetService feedGetService;
    @RequestMapping(value = "/UserFeedPublicFeedGet",method = RequestMethod.GET)
    public Object publicFeed(){
      return   feedGetService.publicFeeds();
    }
    @RequestMapping(value = "/UserFeedFriendFeedGet",method = RequestMethod.GET)
    public Object friendFeed(@RequestParam String token) {
        return feedGetService.privateFeed(token);
    }

    @RequestMapping(value = "/UserFeedDeleteFeedGet/{id}",method = RequestMethod.DELETE)
    public  String delete(@RequestParam("id") long id,@RequestParam String token){
        return  feedGetService.userFeedDelete(id,token);
    }
}
