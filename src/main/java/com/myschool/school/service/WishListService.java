package com.myschool.school.service;

import com.myschool.school.dto.ProductDto;
import com.myschool.school.model.Users;
import com.myschool.school.model.WishList;
import com.myschool.school.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class WishListService {

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    private ProductService productService;


    public void createWishlist(WishList wishList) {

        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishList(Users users) {

      final List<WishList> wishLists =   wishListRepository.findAllByUsersOrderByCreateDateDesc(users);
      List<ProductDto> productDtos = new ArrayList<>();
      for (WishList wishList:wishLists){
          productDtos.add(productService.getProductDto(wishList.getProduct()));
      }
      return productDtos;
    }
}
