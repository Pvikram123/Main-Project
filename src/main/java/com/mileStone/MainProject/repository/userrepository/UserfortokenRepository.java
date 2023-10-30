package com.mileStone.MainProject.repository.userrepository;


import com.mileStone.MainProject.dtos.userdtos.UserForTokenDTOs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserfortokenRepository extends JpaRepository<UserForTokenDTOs,Long> {
}
