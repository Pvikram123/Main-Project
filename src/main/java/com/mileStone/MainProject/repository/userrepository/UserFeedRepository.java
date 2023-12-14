package com.mileStone.MainProject.repository.userrepository;


import com.mileStone.MainProject.models.usermodel.UserFeed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserFeedRepository extends JpaRepository<UserFeed,Long> {

    UserFeed findByUserId(Long userId);
    List<UserFeed> findByVisibility(String visibility);
}
