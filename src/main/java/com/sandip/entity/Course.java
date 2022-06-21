package com.sandip.entity;

// import java.util.ArrayList;
import java.util.HashSet;
// import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "courseName" }) })
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long courseId;
    @NotBlank(message = "Sorry, name field is invalid !!")
    @NotEmpty(message = "Sorry, name field is invalid !!")
    @Size(min = 2, max = 20)
    @NotNull(message = "Sorry, name field is invalid !!")
    private String courseName;

    @Column(nullable = false)
    @Size(max = 200, message = "Course description size must be between 0 and 200 !!")
    private String courseDesc;

    @NotBlank(message = "Sorry, Course Duration is invalid !!")
    @Pattern(regexp = "[0-9]+", message = "Fill your course duration !!")
    @Column(unique = true)
    private String courseDuration;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "course")
    private Set<User> user = new HashSet<User>();

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "course")
    private Set<CourseBranch> branch = new HashSet<CourseBranch>();

    private String addAt;

    public Course() {
    }

    public Course(long courseId, String courseName, String courseDesc, String courseDuration, Set<User> user,
            Set<CourseBranch> branch, String addAt) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDesc = courseDesc;
        this.courseDuration = courseDuration;
        this.user = user;
        this.branch = branch;
        this.addAt = addAt;
    }

    /**
     * @return long return the courseId
     */
    public long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return String return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return String return the courseDesc
     */
    public String getCourseDesc() {
        return courseDesc;
    }

    /**
     * @param courseDesc the courseDesc to set
     */
    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    /**
     * @return String return the courseDuration
     */
    public String getCourseDuration() {
        return courseDuration;
    }

    /**
     * @param courseDuration the courseDuration to set
     */
    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
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
     * @return Set<CourseBranch> return the branch
     */
    public Set<CourseBranch> getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(Set<CourseBranch> branch) {
        this.branch = branch;
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
        return "Course [addAt=" + addAt + ", branch=" + branch + ", courseDesc=" + courseDesc + ", courseDuration="
                + courseDuration + ", courseId=" + courseId + ", courseName=" + courseName + ", user=" + user + "]";
    }

}