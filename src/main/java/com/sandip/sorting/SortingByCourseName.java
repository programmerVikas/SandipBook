package com.sandip.sorting;

import java.util.Comparator;

import com.sandip.entity.Course;

public class SortingByCourseName implements Comparator<Course> {

    @Override
    public int compare(Course c1, Course c2) {
        return c1.getCourseName().compareToIgnoreCase(c2.getCourseName());
    }

}
