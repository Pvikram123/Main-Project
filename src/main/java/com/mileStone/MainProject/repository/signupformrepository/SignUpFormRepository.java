package com.mileStone.MainProject.repository.signupformrepository;

import com.mileStone.MainProject.models.SignUpForm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("SignUpForm")
public interface SignUpFormRepository extends JpaRepository<SignUpForm,String> {

    SignUpForm findByEmailIdIgnoreCase(String userEmail);


//    SignUpForm findById(int userID);
}
