package com.sandip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.sandip.dao.UserCommentDao;
import com.sandip.entity.UserComment;

@Service
public class UserCommentDaoImpl {

    @Autowired
    @Lazy
    private UserCommentDao userCommentDao;

    // saving comment
    public void saveComment(UserComment userComment) {
        userCommentDao.save(userComment);
    }

    // fetching all comment data !!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public List<UserComment> gettingCommentData() {
        return userCommentDao.findAll();
    }

}
