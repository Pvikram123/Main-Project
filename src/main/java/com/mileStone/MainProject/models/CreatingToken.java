package com.mileStone.MainProject.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "CreatingToken")
public class CreatingToken {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tokenId;

    @Column(name = "conformationToken")
    private String confirmationToken;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @OneToOne
    @JoinColumn(name = "userid",nullable = false)
    private SignUpForm signUpForm;

    public CreatingToken() {
    }

    public CreatingToken( SignUpForm signUpForm) {
        confirmationToken = UUID.randomUUID().toString();
        createdDate = new Date();
        this.signUpForm = signUpForm;
    }
}
