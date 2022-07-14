package com.sandip.dao;

import com.sandip.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
    

    public User findByEmail(String email);

    public User findByUserId(Long id);


}