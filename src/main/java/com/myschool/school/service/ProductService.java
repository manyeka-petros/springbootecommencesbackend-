package com.myschool.school.service;

import com.myschool.school.dto.ProductDto;
import com.myschool.school.model.Category;
import com.myschool.school.model.Product;
import com.myschool.school.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProductName(product.getProductName());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        productDto.setProductId(product.getProductId());
        productDto.setCategoryId(product.getCategory().getCategoryId());
        return productDto;

    }


    public List<ProductDto> getAllProduct() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product :products){
            productDtos.add(getProductDto(product));

        }
        return productDtos;
    }

    public void updateProduct(ProductDto productDto, Long productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent()){
            throw new Exception("product is not found ");
        }
        Product product1 = product.get();
        product1.setProductName(productDto.getProductName());
        product1.setDescription(productDto.getDescription());
        product1.setPrice(productDto.getProductId());
        product1.setImageUrl(productDto.getImageUrl());

        productRepository.save(product1);

    }
}
