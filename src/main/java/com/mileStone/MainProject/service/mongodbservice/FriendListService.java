package com.mileStone.MainProject.service.mongodbservice;

import com.mileStone.MainProject.models.mongoDBmodel.FriendList;
import com.mileStone.MainProject.models.usermodel.User;
import com.mileStone.MainProject.models.usermodel.UserRequest;
import com.mileStone.MainProject.repository.mongodbrepository.FriendListRepository;
import com.mileStone.MainProject.repository.userrepository.UserRepository;
import com.mileStone.MainProject.repository.userrepository.UserRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Service
public class FriendListService {
    @Autowired
    FriendListRepository friendListRepository;
    @Autowired
    UserRequestRepository userRequestRepository;
    @Autowired
    UserRepository userRepository;

    long num=1;
    public Object friendList(String token){

        User data1=userRepository.findByTokenUpdate(token);
        String data2=data1.getUserNameData();
        List<UserRequest> data= userRequestRepository.findAllByFollow(data2);
        List<UserRequest> list1=userRequestRepository.findByGivenByName(data2);
        List<String> data4= data.stream().map(q-> q.getGivenByName()).collect(Collectors.toList());
        List<String> list2=list1.stream().filter(a->a.isEnable()).map(a-> a.getFollow() ).collect(Collectors.toList());
        List<String> list3=list1.stream().filter(a->!a.isEnable()).map(a->a.getFollow() ).collect(Collectors.toList());
        FriendList data3=new FriendList();
        data3.setId(num);
        data3.setUserName(data2);
        data3.setFollower(data4);
        data3.setFollowing(list2);
        data3.setRequest(list3);
        FriendList data6=friendListRepository.save(data3);
        num++;
        return data6;
    }
}
