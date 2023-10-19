package com.mileStone.MainProject.dtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class UserDto {
    private String Username;
    private String Dateofbirth;
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

}
