package com.sandip.service;

import java.util.List;

import com.sandip.dao.UserDao;
import com.sandip.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class UserDaoImpl {
    
    @Lazy
    @Autowired
    private UserDao uDao;

// saving user register data********************************** 
    public User saveUser(User user){
       return uDao.save(user);
    }

    // fetching all user from database*********************************
    public List<User> GettingAllUser(){
        return uDao.findAll();
    }


    // fetching data by email-id*******************************
    public User userByEmail(String email){
        return uDao.findByEmail(email);
    }


}