package com.mileStone.MainProject.repository.userrepository;


import com.mileStone.MainProject.models.usermodel.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRequestRepository extends JpaRepository<UserRequest,Long> {
    List<UserRequest> findByGivenByName(String givenByName);
    List<UserRequest> findAllByFollow(String follow);
      UserRequest findByIsEnable(boolean enable);

      UserRequest deleteByGivenByName(String givenByName);
}
