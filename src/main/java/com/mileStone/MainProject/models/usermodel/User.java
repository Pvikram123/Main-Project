package com.mileStone.MainProject.models.usermodel;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {
    @Id
    @Column(name = "INC")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;
    private String  userName;
    private String  firstName;
    private String  lastName;
    private String  emailId;
    private String  phoneNumber;
    private String  password;
    private String  reEntryPassword;
    private String  tokenupdate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "join_data",referencedColumnName = "userName")
    private List<UserRequest> userRequest;





}
