package com.mileStone.MainProject.repository.mongodbrepository;

import com.mileStone.MainProject.models.mongoDBmodel.FriendList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendListRepository extends MongoRepository<FriendList,Long> {
}
