package com.sandip.service;

import com.sandip.dao.UserRoleDao;
import com.sandip.entity.UserRole;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleImpl {
    

    @Autowired
    private UserRoleDao userRoleDao;

    // getting data by role from the database
    public UserRole gettingUserRole(String role){
        return userRoleDao.findByRole(role);
    }

    // getting all role 
    public List<UserRole> gettingAllUserRole(){
        return userRoleDao.findAll();
    }



}