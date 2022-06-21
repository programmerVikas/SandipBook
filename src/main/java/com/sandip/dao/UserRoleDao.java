package com.sandip.dao;

import com.sandip.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long>{
    
    public UserRole findByRole(String role);


}