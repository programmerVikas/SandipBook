package com.sandip.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class UserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long comId;
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserPost post;

    private String commentAt;

    public UserComment() {
    }

    public UserComment(long comId, String comment, User user, UserPost post, String commentAt) {
        this.comId = comId;
        this.comment = comment;
        this.user = user;
        this.post = post;
        this.commentAt = commentAt;
    }

    // generating getter & Setter
    // method******************************************************

    /**
     * @return long return the comId
     */
    public long getComId() {
        return comId;
    }

    /**
     * @param comId the comId to set
     */
    public void setComId(long comId) {
        this.comId = comId;
    }

    /**
     * @return String return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return UserPost return the post
     */
    public UserPost getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(UserPost post) {
        this.post = post;
    }

    /**
     * @return String return the commentAt
     */
    public String getCommentAt() {
        return commentAt;
    }

    /**
     * @param commentAt the commentAt to set
     */
    public void setCommentAt(String commentAt) {
        this.commentAt = commentAt;
    }

    @Override
    public String toString() {
        return "UserComment [comId=" + comId + ", comment=" + comment + ", commentAt=" + commentAt + ", post=" + post
                + ", user=" + user + "]";
    }

    

}