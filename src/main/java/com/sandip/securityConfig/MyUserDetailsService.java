package com.sandip.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sandip.entity.User;
import com.sandip.service.UserDaoImpl;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDaoImpl userDaoImpl;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        User userByEmail = userDaoImpl.userByEmail(email);

        if (userByEmail == null) {
            throw new UsernameNotFoundException("404 not found !!");
        }
        return new UserPrinciple(userByEmail);
    }
}
