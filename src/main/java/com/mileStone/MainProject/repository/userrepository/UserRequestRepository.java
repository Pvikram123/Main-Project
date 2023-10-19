package com.mileStone.MainProject.repository.userrepository;


import com.mileStone.MainProject.models.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest,Long> {
    UserRequest findByGivenByName(String givenByName);
      UserRequest findByIsEnable(boolean enable);

      UserRequest deleteByGivenByName(String givenByName);
}
