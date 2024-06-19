package com.myschool.school.service;

import com.myschool.school.model.Category;
import com.myschool.school.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorySevice {


    @Autowired
     CategoryRepository categoryRepository;

    public void   createCategory(Category category){
         categoryRepository.save(category);
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public void updateCategory(Long categoryId, Category category) {
        Category category1 = categoryRepository.findById(categoryId).get();
        category1.setCategoryName(category.getCategoryName());
        category1.setDescription(category.getDescription());
        category1.setImageUrl(category.getImageUrl());
        categoryRepository.save(category1);
    }

    public boolean findById(Long categoryId) {
      return   categoryRepository.findById(categoryId).isPresent();
    }
}
