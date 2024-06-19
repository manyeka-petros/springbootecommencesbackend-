package com.myschool.school.controller;

import com.myschool.school.common.ApiResponse;
import com.myschool.school.model.Product;
import com.myschool.school.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/saveproduct")

    public ResponseEntity<ApiResponse> saveProduct(@RequestBody Product product){

        return new ResponseEntity<>(new ApiResponse( true,"save to database"), HttpStatus.OK);
    }
}
