package com.mileStone.MainProject.repository.signupformrepository;

import com.mileStone.MainProject.models.SignUpForm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("SignUpForm")
public interface SignUpFormRepository extends CrudRepository<SignUpForm,String> {

    SignUpForm findByEmailIdIgnoreCase(String userEmail);


}
