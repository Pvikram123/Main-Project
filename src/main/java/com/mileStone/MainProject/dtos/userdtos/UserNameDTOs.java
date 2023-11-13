package com.mileStone.MainProject.dtos.userdtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserNameDTOs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  userName;
    private String password;
}
