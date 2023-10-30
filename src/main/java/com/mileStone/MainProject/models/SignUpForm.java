package com.mileStone.MainProject.models;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;


import lombok.Data;


@Data
@Entity
@Table(name = "SignUpForm")
public class SignUpForm {
    @Id
    @Column(name = "UserModel")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @NotEmpty(message = "FirstName is not Null")
    private String userFirstName;

    @NotEmpty(message = "LastName is not Null")
    private String userLastName;

    private String emailId;

    @NotEmpty(message = "Password is not Null")
    private String userPassword;
    //check two password are equal
    private String userReEntryPassword;

    @NotEmpty(message = "Gender is not Null")
    private String userGender;

    @NotEmpty(message = "DateOfBirth is not Null")
    private  String userDateOfBirth;

    private  boolean isEnabled;
    private String salt;


}
