package com.example.affablebean.ds;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode
public class ProductDto {
    private Integer id;
    private String name;
    private double price;
    private int quantity=1;
    private String description;
    private LocalDateTime last_update;
    private int categoryId;
    private List<Integer> productNumberList=new LinkedList<>();

    public ProductDto() {
    }

    public ProductDto(Integer id, String name, double price, String description, LocalDateTime last_update, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description=description;
        this.last_update=last_update;
        this.categoryId = categoryId;
    }
}
