package com.sandip.service;

import java.util.List;

import com.sandip.dao.CourseDao;
import com.sandip.entity.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseDaoImpl {

    @Lazy
    @Autowired
    private CourseDao courseDao;

    // saving course data !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public Course saveCourseData(Course course) {
        return courseDao.save(course);
    }

    // fetching course data from the database with pageble
    public Page<Course> gettingCourseData(Pageable pageable) {
        return courseDao.findAll(pageable);
    }

    // fetching course data from database
    public List<Course> gettingCourseDataList() {
        return courseDao.findAll();
    }

    // deleting course data by id !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void deleteCourseData(Long id) {
        courseDao.deleteById(id);
    }

    // updating course data !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public Course updateCourseData(Course course) {
        return courseDao.save(course);
    }

    // getting course data by course id
    public Course getCourseById(Long id) {
        return courseDao.findByCourseId(id);
    }

}
