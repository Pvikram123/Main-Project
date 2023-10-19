package com.mileStone.MainProject.models;

import jakarta.persistence.*;
import lombok.Data;

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
    private String  following;
    private String   followers;
    private String  tokenUpdate;


//    @OneToMany
//    @JoinColumn(name = "edit",referencedColumnName = "INC")
//    private List<UserRequest> userRequest;


}
