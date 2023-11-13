package com.mileStone.MainProject.models.mongoDBmodel;

import com.mileStone.MainProject.models.usermodel.UserRequest;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Data
public class FriendList {
    @Id
    private long id;
    private String userName;
    private List follower;
    private List following;
    private List request;



}
