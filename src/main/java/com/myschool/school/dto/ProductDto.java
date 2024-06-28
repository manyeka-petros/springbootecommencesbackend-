package com.myschool.school.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productId;

    private Long categoryId;
    private  String productName;
    private  String  description;
    private  String imageUrl;
    private  double price ;

}
