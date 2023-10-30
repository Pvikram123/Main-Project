package com.mileStone.MainProject.models.usermodel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UserRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long requestCount;
    private String follow;
    private String givenByName;
    private boolean isEnable;
}
