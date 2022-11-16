package com.example.affablebean.ds;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderCode;
    private double totalAmount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate CustomerBuyDate;
    @ManyToOne
    private Customer customer;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Product> productList=new ArrayList<>();

    public void addProduct(Product product){
        product.getOrderList().add(this);
        productList.add(product);
    }
}
