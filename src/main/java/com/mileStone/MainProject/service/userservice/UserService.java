package com.mileStone.MainProject.service.userservice;


import com.mileStone.MainProject.config.JwtConfigurationForUser;
import com.mileStone.MainProject.dtos.userdtos.UserForTokenDTOs;
import com.mileStone.MainProject.dtos.userdtos.UserNameDTOs;
import com.mileStone.MainProject.models.usermodel.CreatingTokenUser;
import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.models.usermodel.UserRequest;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import com.mileStone.MainProject.repository.userrepository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserRequestRepository userRequestRepository;
    @Autowired
    JwtConfigurationForUser jwtConfigurationForUser;


    public Object data(User user) {
        User allReadyThere = userRepository.findByUserNameDataIgnoreCase(user.getUserNameData());
        if (allReadyThere != null) {
            return ("username is already there");
        }
        String name="User";
        User data = new User();
        data.setEmailId(user.getEmailId());
        data.setPassword(user.getPassword());
        data.setFirstName(user.getFirstName());
        data.setUserNameData(user.getUserNameData());
        data.setPhoneNumber(user.getPhoneNumber());
        data.setLastName(user.getLastName());
        data.setReEntryPassword(user.getReEntryPassword());
        data.setRole(user.getRole());

        userRepository.save(data);
        return ("its done");


    }

    public List<Object> getUsernames(String token) {

        User name=userRepository.findByTokenUpdate(token);
        List<User> users = userRepository.findAll();

        return users.stream().map(m->m.getUserNameData()).collect(Collectors.toList());

    }
    public Object login(User data) {
        User data1 = userRepository.findByUserNameData(data.getUserNameData());
        if (data1 != null) {
            String test1 = data1.getPassword();
            String test2 = data.getPassword();
            if (test1.equals(test2)) {

                UserForTokenDTOs data3 = new UserForTokenDTOs();
                data3.setUserName(data1.getUserNameData());
                CreatingTokenUser creatingTokenUser = new CreatingTokenUser(data3);
                data1.setTokenUpdate(creatingTokenUser.getCreatingConfirmationToken());
                userRepository.save(data1);
                HttpHeaders headers=new HttpHeaders();
                headers.set("Authorization",""+jwtConfigurationForUser.creatingJWT(data));
                return headers;
                //return ("http://localhost:5000/friendRequestUser?token=" + creatingTokenUser.getCreatingConfirmationToken());

            } else {
                return ("password is wrong");

            }

        }
        return ("username is not valid");

    }

    public Object friendRequest(String data, UserNameDTOs user) {
        User allData = userRepository.findByTokenUpdate(data);
        if (allData != null) {
            UserRequest friend = new UserRequest();
            friend.setFollow(user.getUserName());
            friend.setGivenByName(allData.getUserNameData());
            userRequestRepository.save(friend);
            return ("Request is Send");
        }
        return ("token not found");

    }

    public Object friendRequestGetting(String data) {
        User data1 = userRepository.findByTokenUpdate(data);
        return userRequestRepository.findByGivenByName(data1.getUserNameData());
    }

    public Object acceptFriendRequest(UserNameDTOs userNameDTOs,String token) {
        User data=userRepository.findByTokenUpdate(token);
       List<UserRequest> data1=userRequestRepository.findAllByFollow(data.getUserNameData());
        List<UserRequest> filteredRequests = data1.stream()
                .filter(request -> request.getGivenByName().equals(userNameDTOs.getUserName()))
                .toList();
        if (!filteredRequests.isEmpty()) {
            UserRequest data3 = filteredRequests.get(0);
            data3.setEnable(true);
            userRequestRepository.save(data3);
            return data1;

        }
        return ("request is never accepted");

    }

    public String rejectFriendRequest(UserNameDTOs userNameDTOs,String token) {
        User data1=userRepository.findByTokenUpdate(token);
        List<UserRequest> data2=userRequestRepository.findAllByFollow(data1.getUserNameData());
        List<UserRequest> data3=data2.stream()
                .filter(a->a.getFollow().equals(userNameDTOs.getUserName()))
                .toList();
        UserRequest data4 = data3.get(0);
        userRequestRepository.deleteByGivenByName(data4.getGivenByName());
        return ("its deleted");
    }
    public String logout(){
    return null;
    }
}
