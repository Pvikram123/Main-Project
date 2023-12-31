package com.mileStone.MainProject.models.usermodel;

import com.mileStone.MainProject.dtos.userdtos.UserForTokenDTOs;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "CreatingToken")
public class CreatingTokenUser {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or GenerationType.AUTO
    private int tokenId;

    @Column(name = "conformationToken")
    private String creatingConfirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "userid")
    private UserForTokenDTOs userForTokenDTOs;

    private  String username;
    public CreatingTokenUser() {
    }

    public CreatingTokenUser(UserForTokenDTOs user) {
        creatingConfirmationToken = UUID.randomUUID().toString();
        createdDate = new Date();
        this.userForTokenDTOs = user;
    }
}
