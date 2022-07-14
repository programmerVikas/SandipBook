package com.sandip.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandip.entity.Category;
import com.sandip.entity.User;
import com.sandip.entity.UserPost;

@Repository
public interface UserPostDao extends JpaRepository<UserPost, Long> {

    // getting by category
    public List<UserPost> findByCategory(Category category);

    // for pagination
    public Page<UserPost> findByCategory(Category category, Pageable pageable);

    //getting post data by user
    public Page<UserPost> findByUser(User user, Pageable pageable);


    

}
