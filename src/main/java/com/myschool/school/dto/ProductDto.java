package com.myschool.school.dto;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {


    private  String productName;
    private  String  description;
    private  String imageUrl;
    private  double price ;
    @Id
    private Long productId;

    private Long categoryId;
}
