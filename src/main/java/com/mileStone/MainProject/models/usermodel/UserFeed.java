package com.mileStone.MainProject.models.usermodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "UserFeeds")
@JsonIgnoreProperties(value = {"userId","visibility"})
public class UserFeed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;
    private long userId;
    private String title;
    private String description;
    @Lob
    @Column(name = "imageData",length = 10000000)
    private byte[] imageData;
    private String category;
    private String visibility;
    private String tags;

}
