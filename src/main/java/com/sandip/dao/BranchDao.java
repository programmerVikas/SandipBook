package com.sandip.dao;

import java.util.List;

import com.sandip.entity.Course;
import com.sandip.entity.CourseBranch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchDao extends JpaRepository<CourseBranch, Long> {
    


    public Boolean existsByBranchNameAndCourse(String branchName, Course course);


    public List<CourseBranch> findByCourse(Course course);


}
