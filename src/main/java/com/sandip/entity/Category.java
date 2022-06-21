package com.sandip.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"categoryName"})})
public class Category {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cId;

    @NotBlank(message = "Field is empty !!")
    @NotEmpty(message = "Field is empty !!")
    @NotNull
    @Size(min = 2, message = "Min character should be atleast 2 !!")
    private String categoryName;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "category")
    private Set<UserPost> post = new HashSet<UserPost>();

    private String addAt;

    public Category() {
    }

    public Category(final long cId, final String categoryName, final Set<UserPost> post, final String addAt) {
        this.cId = cId;
        this.categoryName = categoryName;
        this.post = post;
        this.addAt = addAt;
    }

    


    /**
     * @return long return the cId
     */
    public long getCId() {
        return cId;
    }

    /**
     * @param cId the cId to set
     */
    public void setCId(final long cId) {
        this.cId = cId;
    }

    /**
     * @return String return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return List<UserPost> return the post
     */
    public Set<UserPost> getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(final Set<UserPost> post) {
        this.post = post;
    }

    /**
     * @return String return the addAt
     */
    public String getAddAt() {
        return addAt;
    }

    /**
     * @param addAt the addAt to set
     */
    public void setAddAt(final String addAt) {
        this.addAt = addAt;
    }

    @Override
    public String toString() {
        return "Category [addAt=" + addAt + ", cId=" + cId + ", categoryName=" + categoryName + ", post=" + post + "]";
    }

    

}