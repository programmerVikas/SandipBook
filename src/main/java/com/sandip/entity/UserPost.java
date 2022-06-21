package com.sandip.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UserPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long postId;
    private String post;
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "post")
    private Set<UserComment> comments = new HashSet<UserComment>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    private String postOn;

    public UserPost() {
    }

    public UserPost(long postId, String post, String photo, User user, Set<UserComment> comments, Category category,
            String postOn) {
        this.postId = postId;
        this.post = post;
        this.photo = photo;
        this.user = user;
        this.comments = comments;
        this.category = category;
        this.postOn = postOn;
    }

    /**
     * @return long return the postId
     */
    public long getPostId() {
        return postId;
    }

    /**
     * @param postId the postId to set
     */
    public void setPostId(long postId) {
        this.postId = postId;
    }

    /**
     * @return String return the post
     */
    public String getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(String post) {
        this.post = post;
    }

    /**
     * @return String return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @param photo the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
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
     * @return Set<UserComment> return the comments
     */
    public Set<UserComment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(Set<UserComment> comments) {
        this.comments = comments;
    }

    /**
     * @return Category return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * @return String return the postOn
     */
    public String getPostOn() {
        return postOn;
    }

    /**
     * @param postOn the postOn to set
     */
    public void setPostOn(String postOn) {
        this.postOn = postOn;
    }

    @Override
    public String toString() {
        return "UserPost [category=" + category + ", comments=" + comments + ", photo=" + photo + ", post=" + post
                + ", postId=" + postId + ", postOn=" + postOn + ", user=" + user + "]";
    }

    


}