package com.mileStone.MainProject.repository.userrepository;

import com.mileStone.MainProject.models.Followers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowersRepository extends JpaRepository<Followers,Long> {
    Followers deleteByFollowing(long following);
    Followers findByFollowing(long following);
}
