package com.mileStone.MainProject.repository.userrepository;


import com.mileStone.MainProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameIgnoreCase(String userName);
     User findByUserName(String userName);
    User findByTokenupdate(String tokenupdate );



}
