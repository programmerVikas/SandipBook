package com.sandip.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandip.entity.UserComment;

@Repository
public interface UserCommentDao extends JpaRepository<UserComment, Long> {
    
}
