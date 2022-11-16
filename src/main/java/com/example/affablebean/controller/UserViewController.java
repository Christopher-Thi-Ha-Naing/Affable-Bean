package com.example.affablebean.controller;

import com.example.affablebean.dao.CategoryDao;
import com.example.affablebean.dao.ProductDao;
import com.example.affablebean.service.CartService;
import com.example.affablebean.service.CategoryService;
import com.example.affablebean.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserViewController {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;

    @ModelAttribute("cartSize")
    public int cartSize(){
        return cartService.cartSize();
    }

    @GetMapping(value = {"/", "/home"})
    public String index(Model model){
        return "home";
    }

    @GetMapping("/user/product/list")
    public String homeDairyList(@RequestParam("id") int id, Model model){
        model.addAttribute("product", productDao.findProductById(id));
        model.addAttribute("category",categoryService.findCategoryById(id));
        return "product";
    }



}
