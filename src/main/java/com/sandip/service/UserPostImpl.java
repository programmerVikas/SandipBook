package com.sandip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sandip.dao.UserPostDao;
import com.sandip.entity.Category;
import com.sandip.entity.UserPost;

@Service
public class UserPostImpl {

    @Lazy
    @Autowired
    private UserPostDao userPostDao;

    // Saving post data !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void savePost(UserPost uPost) {
        userPostDao.save(uPost);
    }

    // fetching post all data from database on
    // pageble********************************
    public Page<UserPost> findByCategoryDataPagination(Pageable pageable, Category category) {
        return userPostDao.findByCategory(category, pageable);
    }

    // getting post by category
    public List<UserPost> findByCategoryData(Category category) {
        return userPostDao.findByCategory(category);
    }

    // getting all user post data!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public List<UserPost> getAllUserPosts() {
        return userPostDao.findAll();
    }

}
