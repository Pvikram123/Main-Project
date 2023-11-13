package com.mileStone.MainProject.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Followers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
 //   private long UserId;
    private long follower;
    private  long following;
}
