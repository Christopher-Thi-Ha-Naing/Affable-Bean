package com.example.affablebean.controller;

import com.example.affablebean.dao.CategoryDao;
import com.example.affablebean.ds.Cart;
import com.example.affablebean.ds.Product;
import com.example.affablebean.ds.ProductDto;
import com.example.affablebean.service.CartService;
import com.example.affablebean.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private Cart cart;

    private List<Integer> productQuantityList;
    int catId;

    @ModelAttribute("cartSize")
    public int cartSize() {
        return cartService.cartSize();
    }





    @PostMapping("/cart/calculate")
    public String cartPrice(ProductDto productDto, Model model) {
        this.productQuantityList = productDto.getProductNumberList();
        model.addAttribute("cartItem", cartService.listCart());
        model.addAttribute("cartSize", cartService.cartSize());
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("productQuantityList", productQuantityList);
        System.out.println(productQuantityList);
        return "cart-view";
    }

    @GetMapping("/cart/add")
    public String addToCart(@RequestParam("id") int id, Model model) {
        cartService.addToCart(productService.findProductById(id));
        catId = categoryDao.findCategoryByProductId(id);
        return "redirect:/user/product/list?id=" + catId;
    }

    @GetMapping("/cart/view")
    public String viewCart(Model model) {
        model.addAttribute("cartItem", cartService.listCart());
        model.addAttribute("cartSize", cartService.cartSize());
        model.addAttribute("productDto", new ProductDto());
        model.addAttribute("productQuantityList", productQuantityList);
        return "cart-view";
    }

    @GetMapping("/cart/delete/{id}")
    public String removeFromCart(@PathVariable("id") int id, Model model) {
        model.addAttribute("cartSize", cartService.cartSize());
        Product product = productService.findProductById(id);
        cartService.remove(cartService.toDto(product));
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart/view";
    }

    @GetMapping("/cart/shopping")
    public String continueShopping() {
        return "redirect:/user/product/list?id=" + catId;
    }


}
