package com.mileStone.MainProject.repository.userrepository;


import com.mileStone.MainProject.models.usermodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserNameDataIgnoreCase(String userName);
     User findByUserNameData(String userName);
    User findByTokenUpdate(String tokenUpdate );



}
