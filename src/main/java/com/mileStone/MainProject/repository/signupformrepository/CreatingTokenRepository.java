package com.mileStone.MainProject.repository.signupformrepository;

import com.mileStone.MainProject.models.CreatingToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatingTokenRepository extends CrudRepository<CreatingToken,String> {
    CreatingToken findByConfirmationToken(String confirmationToken);

}
