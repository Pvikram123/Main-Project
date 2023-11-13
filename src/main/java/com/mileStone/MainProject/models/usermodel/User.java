package com.mileStone.MainProject.models.usermodel;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
public class User implements UserDetails {
    @Id
    @Column(name = "INC")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userNameData;
    private String firstName;
    private String lastName;
    private String emailId;
    private String phoneNumber;
    private String password;
    private String reEntryPassword;
    private String tokenUpdate;
    private String role;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "join_data", referencedColumnName = "userNameData")
    private List<UserRequest> userRequest;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
        return Collections.singletonList(simpleGrantedAuthority);


    }

    @Override
    public String getUsername() {
        return userNameData;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
