package com.project3.Ecommerce.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project3.Ecommerce.Model.CustomerUserDetails;
import com.project3.Ecommerce.Model.User;
import com.project3.Ecommerce.Repository.UserRepository;

@Service
public class CustomerUserDetailService  implements UserDetailsService{

    @Autowired 
    UserRepository userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findUserByEmail(username);
        user.orElseThrow(()->new UsernameNotFoundException("Not Found"));
        return user.map(CustomerUserDetails::new).get();
    }
    
}
