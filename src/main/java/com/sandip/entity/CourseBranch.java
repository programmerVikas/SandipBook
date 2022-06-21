package com.sandip.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
public class CourseBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long branchId;

    @NotBlank(message = "Field is empty !!")
    @NotEmpty(message = "Field is empty !!")
    @Size(min = 2, message = "Min character should be atleast 2 !!")
    @Column(nullable = false)
    private String branchName;

    @Size(max = 200, message = "Description size must be between 0 and 200 !!")
    private String branchDesc;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "branch")
    private Set<User> user = new HashSet<User>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    private String addAt;

    public CourseBranch() {
    }

    public CourseBranch(long branchId, String branchName, String branchDesc, Set<User> user, Course course,
            String addAt) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.branchDesc = branchDesc;
        this.user = user;
        this.course = course;
        this.addAt = addAt;
    }

    /**
     * @return long return the branchId
     */
    public long getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    /**
     * @return String return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return String return the branchDesc
     */
    public String getBranchDesc() {
        return branchDesc;
    }

    /**
     * @param branchDesc the branchDesc to set
     */
    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }

    /**
     * @return Set<User> return the user
     */
    public Set<User> getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Set<User> user) {
        this.user = user;
    }

    /**
     * @return Course return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(Course course) {
        this.course = course;
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
    public void setAddAt(String addAt) {
        this.addAt = addAt;
    }

    @Override
    public String toString() {
        return "CourseBranch [addAt=" + addAt + ", branchDesc=" + branchDesc + ", branchId=" + branchId
                + ", branchName=" + branchName + ", course=" + course + ", user=" + user + "]";
    }

}