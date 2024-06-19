package com.myschool.school.service;

import com.myschool.school.dto.ProductDto;
import com.myschool.school.model.Category;
import com.myschool.school.model.Product;
import com.myschool.school.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getProductId());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(category);
        productRepository.save(product);
    }
}
