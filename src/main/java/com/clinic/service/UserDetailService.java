package com.clinic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.clinic.model.User;
import com.clinic.repository.UserRepository;
import com.clinic.security.userDetails;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = repository.findByLogin(login);

        if(user == null){
            throw new UsernameNotFoundException("User not found!");
        }else
            return new userDetails(user, user.getPerfis());
    }
    
}
