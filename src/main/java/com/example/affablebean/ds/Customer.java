package com.example.affablebean.ds;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private int prague;
    private String creditCard;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.PERSIST)
    private List<ProductOrder> orders=new ArrayList<>();

    public void addOrderCustomer(ProductOrder order){
        order.setCustomer(this);
        orders.add(order);
    }



}
