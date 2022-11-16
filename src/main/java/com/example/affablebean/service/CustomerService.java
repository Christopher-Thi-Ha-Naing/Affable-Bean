package com.example.affablebean.service;

import com.example.affablebean.dao.CustomerDao;
import com.example.affablebean.dao.ProductOrderDao;
import com.example.affablebean.ds.Customer;
import com.example.affablebean.ds.Product;
import com.example.affablebean.ds.ProductDto;
import com.example.affablebean.ds.ProductOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Random;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductOrderDao productOrderDao;

    public Customer findCustomerByName(String name){
        return customerDao.findCustomerByName(name).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void register(ProductOrder productOrder, Set<ProductDto> productDtoSet, Customer customer){
        for (ProductDto productDto:productDtoSet){
            productOrder.addProduct(cartService.toEntity(productDto));
        }
        ProductOrder productOrder1=productOrderDao.saveAndFlush(productOrder);
        saveProductOrder(productDtoSet,customer);
    }

    public void saveProductOrder(Set<ProductDto> productDtoSet, Customer customer){
        ProductOrder productOrder=new ProductOrder();
        productOrder.setCustomer(customer);
        productOrder.setTotalAmount(totalPrice(productDtoSet));
        productOrder.setOrderCode(generateCode(customer));
        productOrderDao.save(productOrder);
    }


    private String generateCode(Customer customer){
        Random random=new Random();
        int code= random.nextInt(100000) + 100000;
        return customer.getName()+code;
    }

    private double totalPrice(Set<ProductDto> books){
        return books.stream().map(b->b.getPrice()*b.getQuantity()).mapToDouble(d->d).sum();
    }
}
