package com.sandip.service;


import com.sandip.dao.CategoryDao;
import com.sandip.entity.Category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryDaoImpl {

    @Lazy
    @Autowired
    private CategoryDao categoryDao;

    // saving new category data**********************************************
    public Category saveNewCategory(Category category) {
        return categoryDao.save(category);
    }

    // fetching category all data from database on pageble********************************
    public Page<Category> gettingCategoryData(Pageable pageable) {
        return categoryDao.findAll(pageable);
    }

     // fetching category all data from database ********************************
     public List<Category> gettingCategory() {
        return categoryDao.findAll();
    }

    // deleting category by Id****************************************
    public void delete(long id) {
        categoryDao.deleteById(id);
    }

    // updating category by Id****************************************
    public void update(Category category) {
        categoryDao.save(category);
    }

    
}