package com.example.affablebean.dao;

import com.example.affablebean.ds.Category;
import com.example.affablebean.ds.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category,Integer> {

    @Query("select c.id from Category c join Product p on c.id=p.category.id where p.id=:id")
    int findCategoryByProductId(@Param("id") int id);
}
