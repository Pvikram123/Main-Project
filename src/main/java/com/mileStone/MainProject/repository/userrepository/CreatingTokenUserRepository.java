package com.mileStone.MainProject.repository.userrepository;

import com.mileStone.MainProject.models.CreatingTokenUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CreatingTokenUserRepository extends JpaRepository<CreatingTokenUser,Integer> {
       CreatingTokenUser findByConfirmationToken(String confirmationToken);
//       CreatingToken findByUserid(Long userid);

    }

