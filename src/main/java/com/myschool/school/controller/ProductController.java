package com.myschool.school.controller;

import com.myschool.school.common.ApiResponse;
import com.myschool.school.dto.ProductDto;
import com.myschool.school.model.Category;
import com.myschool.school.model.Product;
import com.myschool.school.repository.CategoryRepository;
import com.myschool.school.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping("/saveProduct")

    public ResponseEntity<ApiResponse> saveProduct(@RequestBody ProductDto productDto){
        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        if(!categoryOptional.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"category is not found "),HttpStatus.NOT_FOUND);
        }
        productService.createProduct(productDto,categoryOptional.get());
        return new ResponseEntity<>(new ApiResponse( true,"save to database"), HttpStatus.CREATED);
    }

    @GetMapping("/getProduct")

    public ResponseEntity<List<ProductDto>> getAllProduct(){
        List<ProductDto> product = productService.getAllProduct();
        return  new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/saveProduct/{productId}")

    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        if(!categoryOptional.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"category is not found "),HttpStatus.NOT_FOUND);
        }
        productService.updateProduct(productDto,productId);
        return new ResponseEntity<>(new ApiResponse( true,"save to database"), HttpStatus.CREATED);
    }
}
