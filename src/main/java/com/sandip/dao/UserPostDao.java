package com.sandip.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandip.entity.UserPost;

@Repository
public interface UserPostDao extends JpaRepository<UserPost, Long>{
    
}
