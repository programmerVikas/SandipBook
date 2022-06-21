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
import javax.persistence.OneToMany;


@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleId;
    @Column(name = "role_name", unique = true)
    private String role;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "user_role")
    private Set<User> user = new HashSet<User>();

    public UserRole() {
    }

    public UserRole(long roleId, String role, Set<User> user) {
        this.roleId = roleId;
        this.role = role;
        this.user = user;
    }



    /**
     * @return long return the roleId
     */
    public long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return String return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "UserRole [role=" + role + ", roleId=" + roleId + ", user=" + user + "]";
    }

    

}