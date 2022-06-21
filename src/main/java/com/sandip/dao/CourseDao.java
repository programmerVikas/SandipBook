package com.sandip.dao;

import com.sandip.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends JpaRepository<Course, Long>{
    
    
    public Course findByCourseId(Long id);


}
