package com.example.affablebean.controller;

import com.example.affablebean.ds.ProductDto;
import com.example.affablebean.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private CartService cartService;

    private List<Integer> productQuantityList;

    @GetMapping("/order/checkout")
    public String proceedToCheckout(){
        return "checkout";
    }

}
