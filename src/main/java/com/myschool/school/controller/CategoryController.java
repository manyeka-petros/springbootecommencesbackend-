package com.myschool.school.controller;


import com.myschool.school.common.ApiResponse;
import com.myschool.school.model.Category;
import com.myschool.school.service.CategorySevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "http://localhost:3000")

public class CategoryController {

    @Autowired
    private CategorySevice categorySevice;

    @PostMapping("/createCategory")

    public ResponseEntity<ApiResponse> craeteCategor(@RequestBody Category category){

          categorySevice.createCategory(category);
          return new ResponseEntity<>(new ApiResponse(true, "saved to database"), HttpStatus.OK);
    }
    @GetMapping("/getCategory")
    public List<Category> getAllCategory(){
        return categorySevice.getAllCategory();
    }

    @PutMapping("/category/{categoryId}")

    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId" )Long categoryId, @RequestBody Category category){

        if(!categorySevice.findById(categoryId)){
            return new ResponseEntity<>( new ApiResponse(false, "not found "),HttpStatus.NOT_FOUND);
        }
        categorySevice.updateCategory(categoryId,category);
        return new ResponseEntity<ApiResponse>( new ApiResponse(true, "updated"), HttpStatus.OK);
    }
}
