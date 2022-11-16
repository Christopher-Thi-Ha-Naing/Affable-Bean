package com.example.affablebean.service;

import com.example.affablebean.dao.CategoryDao;
import com.example.affablebean.ds.Cart;
import com.example.affablebean.ds.Product;
import com.example.affablebean.ds.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CartService {

    @Autowired
    private Cart cart;
    @Autowired
    private CategoryDao categoryDao;

    public ProductDto toDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getLast_update(),
                product.getCategory().getId()
        );
    }

    public Product toEntity(ProductDto productDto){
        Product product=new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setLast_update(productDto.getLast_update());
        product.setCategory(categoryDao.findById(productDto.getId()).get());
        return product;
    }

    public void addToCart(Product product){
        cart.addToCart(toDto(product));
    }

    public Set<ProductDto> listCart(){
        return cart.getProductDtos();
    }

    public int cartSize(){
        return cart.cartSize();
    }

    public void remove(ProductDto productDto){
        cart.removeFromCart(productDto);
    }

    public void clearCart(){
        cart.clearCart();
    }

//    Testing methods
//    public int getQuantity(int id){
//        List<ProductDto> productDtoList= (List<ProductDto>) cart.getProductDtos();
//        return productDtoList.get(id).getQuantity();
//    }
//
//    public double getPrice(int id){
//        List<ProductDto> productDtoList= (List<ProductDto>) cart.getProductDtos();
//        return productDtoList.get(id).getPrice();
//    }
//
//    public double calculateTotal(int id){
//        return getQuantity(id) * getPrice(id);
//    }



}
