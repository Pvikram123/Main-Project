package com.mileStone.MainProject.service.userservice;


import com.mileStone.MainProject.dtos.userdtos.UserForTokenDTOs;
import com.mileStone.MainProject.dtos.userdtos.UserNameDTOs;
import com.mileStone.MainProject.models.usermodel.CreatingTokenUser;
import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.models.usermodel.UserRequest;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import com.mileStone.MainProject.repository.userrepository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRequestRepository userRequestRepository;
//    @Autowired
//    CreatingTokenUserRepository creatingTokenUserRepository;


    public Object data(User user) {
        User allReadyThere = userRepository.findByUserNameIgnoreCase(user.getUserName());
        if (allReadyThere != null) {
            return ("username is already there");
        }
        User data = new User();
        data.setEmailId(user.getEmailId());
        data.setPassword(user.getPassword());
        data.setFirstName(user.getFirstName());
        data.setUserName(user.getUserName());
        data.setPhoneNumber(user.getPhoneNumber());
        data.setLastName(user.getLastName());
        data.setReEntryPassword(user.getReEntryPassword());

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
//                creatingTokenUserRepository.save(creatingTokenUser);
                data1.setTokenupdate(creatingTokenUser.getCreatingConfirmationToken());
                userRepository.save(data1);
                return ("http://localhost:5000/friendrequest?Token=" + creatingTokenUser.getCreatingConfirmationToken());

            } else {
                return ("password is wrong");

            }

        }
        return ("username is not valid");

    }

    public Object friendRequest(String data, UserNameDTOs user) {
        User allData = userRepository.findByTokenupdate(data);
        if (allData != null) {
            UserRequest friend = new UserRequest();
            friend.setFollow(user.getUserName());
            friend.setGivenByName(allData.getUserName());
            userRequestRepository.save(friend);
            return ("Request is Send");
        }
        return ("token not found");

    }

    public Object friendRequestGetting(String data) {
        User data1 = userRepository.findByTokenupdate(data);
        return userRequestRepository.findByGivenByName(data1.getUserName());
    }

    public String acceptFriendRequest(UserNameDTOs userNameDTOs) {
        UserRequest name = userRequestRepository.findByGivenByName(userNameDTOs.getUserName());
        name.setEnable(true);
        userRequestRepository.save(name);
        return ("request is accepted");
    }

    public String rejectFriendRequest(UserNameDTOs userNameDTOs) {
        UserRequest name=userRequestRepository.findByGivenByName(userNameDTOs.getUserName());
        userRequestRepository.deleteById(name.getRequestCount());
        return ("request is accepted");
    }
}
