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
    private ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategory(category);
        productRepository.save(product);
    }

    public ProductDto getProductDto(Product product) {
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
        for (Product product : products) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }

    public ProductDto getProductById(Long productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            return getProductDto(product.get());
        } else {
            throw new Exception("Product not found");
        }
    }

    public void updateProduct(ProductDto productDto, Long productId) throws Exception {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new Exception("product is not found");
        }
        Product product1 = product.get();
        product1.setProductName(productDto.getProductName());
        product1.setDescription(productDto.getDescription());
        product1.setPrice(productDto.getPrice());
        product1.setImageUrl(productDto.getImageUrl());
        product1.setCategory(new Category(productDto.getCategoryId(), null, null, null)); // Assuming Category constructor
        productRepository.save(product1);
    }

    public Product findById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new RuntimeException("product is empty");
        }
        return product.get();
    }
}
