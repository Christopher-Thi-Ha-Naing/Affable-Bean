package com.example.affablebean.ds;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@SessionScope
public class Cart {

    private ProductDto productDto=new ProductDto();

    private Set<ProductDto> productDtos=new HashSet<>();

    public int cartSize(){
        return productDtos.size();
    }

    public void addToCart(ProductDto productDto){
        productDtos.add(productDto);
    }

    public Set<ProductDto> getProductDtos(){
        return productDtos;
    }

    public void removeFromCart(ProductDto productDto){
        productDtos.remove(productDto);
    }

    public void clearCart(){
        productDtos.clear();
    }

    public List<Integer> productNumberList(){
        return productDto.getProductNumberList();
    }
}
