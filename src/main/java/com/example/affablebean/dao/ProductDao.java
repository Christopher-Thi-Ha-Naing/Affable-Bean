package com.example.affablebean.dao;

import com.example.affablebean.ds.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where p.category.id=:id")
    List<Product> findProductById(@Param("id") int id);
}
