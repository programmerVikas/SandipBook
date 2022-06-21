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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"email","prn"})})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String firstName;
    private String lastName;

    // userrole
    // mapping**************************************************************
    @ManyToOne(fetch = FetchType.EAGER)
    private UserRole user_role;

    private String email;
    private String password;
    private String profilePic;

    // course
    // mapping******************************************************************
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    // branch
    // mapping***********************************************************************
    @ManyToOne(fetch = FetchType.EAGER)
    private CourseBranch branch;

    private String session;
    private int AcademicYear;
    private String prn;
    private String address;

    // post
    // mapping************************************************************************
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserPost> post = new HashSet<UserPost>();

    // comments
    // mapping*******************************************************************
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user")
    private Set<UserComment> comments = new HashSet<UserComment>();

    private String registereddAt;
    private String updatedAt;

    // non-parameterized
    // constructor*****************************************************
    public User() {
    }

    // parameterized
    // constructor***************************************************************
    public User(long userId, String firstName, String lastName, UserRole user_role, String email, String password,
            String profilePic, Course course, CourseBranch branch, String session, int academicYear, String prn,
            String address, Set<UserPost> post, Set<UserComment> comments, String registereddAt, String updatedAt) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user_role = user_role;
        this.email = email;
        this.password = password;
        this.profilePic = profilePic;
        this.course = course;
        this.branch = branch;
        this.session = session;
        AcademicYear = academicYear;
        this.prn = prn;
        this.address = address;
        this.post = post;
        this.comments = comments;
        this.registereddAt = registereddAt;
        this.updatedAt = updatedAt;
    }

    // Generating getter & setter
    // method******************************************************************

    /**
     * @return long return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return String return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return String return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return UserRole return the user_role
     */
    public UserRole getUser_role() {
        return user_role;
    }

    /**
     * @param user_role the user_role to set
     */
    public void setUser_role(UserRole user_role) {
        this.user_role = user_role;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return String return the profilePic
     */
    public String getProfilePic() {
        return profilePic;
    }

    /**
     * @param profilePic the profilePic to set
     */
    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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
     * @return CourseBranch return the branch
     */
    public CourseBranch getBranch() {
        return branch;
    }

    /**
     * @param branch the branch to set
     */
    public void setBranch(CourseBranch branch) {
        this.branch = branch;
    }

    /**
     * @return String return the session
     */
    public String getSession() {
        return session;
    }

    /**
     * @param session the session to set
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * @return int return the AcademicYear
     */
    public int getAcademicYear() {
        return AcademicYear;
    }

    /**
     * @param AcademicYear the AcademicYear to set
     */
    public void setAcademicYear(int AcademicYear) {
        this.AcademicYear = AcademicYear;
    }

    /**
     * @return String return the prn
     */
    public String getPrn() {
        return prn;
    }

    /**
     * @param prn the prn to set
     */
    public void setPrn(String prn) {
        this.prn = prn;
    }

    /**
     * @return String return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return Set<UserPost> return the post
     */
    public Set<UserPost> getPost() {
        return post;
    }

    /**
     * @param post the post to set
     */
    public void setPost(Set<UserPost> post) {
        this.post = post;
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
     * @return String return the registereddAt
     */
    public String getRegistereddAt() {
        return registereddAt;
    }

    /**
     * @param registereddAt the registereddAt to set
     */
    public void setRegistereddAt(String registereddAt) {
        this.registereddAt = registereddAt;
    }

    /**
     * @return String return the updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt the updatedAt to set
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User [AcademicYear=" + AcademicYear + ", address=" + address + ", branch=" + branch + ", comments="
                + comments + ", course=" + course + ", email=" + email + ", firstName=" + firstName + ", lastName="
                + lastName + ", password=" + password + ", post=" + post + ", prn=" + prn + ", profilePic=" + profilePic
                + ", registereddAt=" + registereddAt + ", session=" + session + ", updatedAt=" + updatedAt + ", userId="
                + userId + ", user_role=" + user_role + "]";
    }


    


}