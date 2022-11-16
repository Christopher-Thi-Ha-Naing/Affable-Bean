package com.example.affablebean.service;

import com.example.affablebean.dao.ProductDao;
import com.example.affablebean.ds.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> findAllProduct(){
        return productDao.findAll();
    }

    public Product findProductById(int id){
        return productDao.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
