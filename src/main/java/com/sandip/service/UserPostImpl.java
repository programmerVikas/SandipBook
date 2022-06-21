package com.sandip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sandip.dao.UserPostDao;
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

    // getting all user post data!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public List<UserPost> getAllUserPosts() {
        return userPostDao.findAll();
    }

}
