package com.mileStone.MainProject.service.userservice;


import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.models.usermodel.UserFeed;
import com.mileStone.MainProject.repository.userrepository.UserFeedRepository;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Stream;

@Service  
public class FeedGetService {
    @Autowired
    UserFeedRepository userFeedRepository;
    @Autowired
    UserRepository userRepository;

    public Object publicFeeds() {
        String visibility = "Public";
        return userFeedRepository.findByVisibility(visibility);
    }
    public Object privateFeed(String token) {
        String visibility = "Private";

        User information=userRepository.findByTokenUpdate(token);
        long number=information.getId();
        if (information!=null){
            List<UserFeed> data=userFeedRepository.findByVisibility(visibility);
            Stream<UserFeed> data1=data.stream();
          return  data1.filter(getting ->getting.getUserId()==number);
        }
        return ("the data is private");
    }
    public  String userFeedDelete(long id,String token){
        User information=userRepository.findByTokenUpdate(token);
        if ((information!=null)) {
            userFeedRepository.deleteById(id);
            return ("its deleted");
        }
        return ("login please");
    }


}
