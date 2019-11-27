/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.leye.services;

import com.example.leye.domain.User;
import com.example.leye.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Leyeseyi
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepo;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email);
        CustomUserDetails userDetails = null;
        
        if(user != null){
            
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);
        }
        else{
            throw  new UsernameNotFoundException("user with email address " + email + "does not exist");
        }
        return userDetails;
    }
    
}
