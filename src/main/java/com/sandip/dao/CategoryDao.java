package com.sandip.dao;

import com.sandip.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {
    

   public Category findByCategoryName(String name);

}