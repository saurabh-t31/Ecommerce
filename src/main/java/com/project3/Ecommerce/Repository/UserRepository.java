package com.project3.Ecommerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project3.Ecommerce.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User>findUserByEmail(String username);
        
    
     
    

}
