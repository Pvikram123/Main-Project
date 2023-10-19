package com.mileStone.MainProject.service;


import com.mileStone.MainProject.dtos.UserForTokenDTOs;
import com.mileStone.MainProject.dtos.UserNameDTOs;
import com.mileStone.MainProject.models.CreatingTokenUser;
import com.mileStone.MainProject.models.Followers;
import com.mileStone.MainProject.models.User;
import com.mileStone.MainProject.models.UserRequest;
import com.mileStone.MainProject.repository.userrepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private long id = 12234;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRequestRepository userRequestRepository;
    @Autowired
    CreatingTokenUserRepository creatingTokenUserRepository;
    @Autowired
    UserfortokenRepository userfortokenRepository;
    @Autowired
    FollowersRepository followersRepository;

    public Object data(User user) {
        User allReadyThere = userRepository.findByUserNameIgnoreCase(user.getUserName());
        if (allReadyThere != null) {
            return ("username is already there");
        }
        String isempty="";
        User data = new User();
        data.setEmailId(user.getEmailId());
        data.setPassword(user.getPassword());
        data.setFirstName(user.getFirstName());
        data.setUserName(user.getUserName());
        data.setPhoneNumber(user.getPhoneNumber());
        data.setLastName(user.getLastName());
        data.setReEntryPassword(user.getReEntryPassword());
        data.setFollowers(isempty);
        data.setFollowing(isempty);

        userRepository.save(data);
        return ("its done");


    }

    public List<String> getUsernames() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> user.getUserName()).collect(Collectors.toList());
    }

    public String login(User data) {
        User data1 = userRepository.findByUserName(data.getUserName());
        if (data1 != null) {
            String test1 = data1.getPassword();
            String test2 = data.getPassword();
            if (test1.equals(test2)) {

                UserForTokenDTOs data3 = new UserForTokenDTOs();
                data3.setUserName(data1.getUserName());
                CreatingTokenUser creatingTokenUser = new CreatingTokenUser(data3);
                creatingTokenUserRepository.save(creatingTokenUser);
                data1.setTokenUpdate(creatingTokenUser.getConfirmationToken());
                userRepository.save(data1);
                return ("http://localhost:5000/friendrequest?Token=" + creatingTokenUser.getConfirmationToken());

            } else {
                return ("password is wrong");

            }

        }
        return ("username is not valid");

    }

    public String friendRequest(String data, UserNameDTOs user) {
        UserNameDTOs data1 = user;
        User allData = userRepository.findByTokenupdate(data);
        if (allData != null) {
            UserRequest friend = new UserRequest();
            friend.setFollow(data1.getUserName());
            friend.setGivenByName(allData.getUserName());
            userRequestRepository.save(friend);
            return ("Request is Send");
        }
        return ("token not found");

    }

    public Object friendRequestGetting(String data) {
        User data1 = userRepository.findByTokenupdate(data);
        String data2 = data1.getUserName();
        return userRequestRepository.findByGivenByName(data2);
    }

    public String acceptFriendRequest(UserNameDTOs UserNameDTOs) {
        UserRequest name = userRequestRepository.findByGivenByName(UserNameDTOs.getUserName());
        name.setEnable(true);
        userRequestRepository.save(name);
        return ("request is accepted");
    }

    public String rejectFriendRequest(UserNameDTOs UserNameDTOs) {
        UserRequest name=userRequestRepository.findByGivenByName(UserNameDTOs.getUserName());

        userRequestRepository.deleteById(name.getRequestCount());

        return ("request is accepted");
    }
    public String following(String data,UserNameDTOs userNameDTOs){
        User followingData=userRepository.findByUserName(userNameDTOs.getUserName());
        User gettingData=userRepository.findByTokenupdate(data);
        Followers getFollower=new Followers();
        getFollower.setFollower(followingData.getId());
        getFollower.setFollowing(gettingData.getId());
        followersRepository.save(getFollower);
        return ("followed");

    }
    public String unFollow(String data,UserNameDTOs userNameDTOs){
        User followingData=userRepository.findByUserName(userNameDTOs.getUserName());
        User gettingData=userRepository.findByTokenupdate(data);
        long followerId=followingData.getId();
        long  followingId=gettingData.getId();
        Followers inFollowerRepository=followersRepository.findByFollowing(followerId);
        long  getFollowerData=inFollowerRepository.getFollower();
        long  getFollowing=inFollowerRepository.getFollowing() ;
        if(followerId==getFollowerData && followingId==getFollowing) {
            followersRepository.deleteByFollowing(followingData.getId());
            return ("Unfollow");
        }
        return ("given information is wrong");
    }

}
