package com.sandip.service;

import java.util.List;

import com.sandip.dao.BranchDao;
import com.sandip.entity.Course;
import com.sandip.entity.CourseBranch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class BranchDaoImpl {

    @Lazy
    @Autowired
    private BranchDao branchDao;

    // saving branch data in database
    public void saveBranchData(CourseBranch courseBranch) {
        branchDao.save(courseBranch);
    }

    public Boolean checkUsernameandCourse(String name, Course course) {
        return branchDao.existsByBranchNameAndCourse(name, course);
    }


    public List<CourseBranch> gettingBranchAllData(){
        return branchDao.findAll();
    }


    public List<CourseBranch> gettingBranchByCourse(Course course){
        return branchDao.findByCourse(course);
    }

    
    public void deleteBranchData(Long id) {
        branchDao.deleteById(id);
    }



}
