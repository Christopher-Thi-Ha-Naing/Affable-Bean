package com.example.affablebean.service;

import com.example.affablebean.dao.CategoryDao;
import com.example.affablebean.ds.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> findAllCategory(){
        return categoryDao.findAll();
    }
    public Category findCategoryById(int id) {
        return categoryDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
