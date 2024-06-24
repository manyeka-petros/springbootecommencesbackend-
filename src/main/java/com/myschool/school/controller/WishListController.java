package com.myschool.school.controller;

import com.myschool.school.common.ApiResponse;
import com.myschool.school.dto.ProductDto;
import com.myschool.school.model.Product;
import com.myschool.school.model.Users;
import com.myschool.school.model.WishList;
import com.myschool.school.service.AuthenticationTokenService;
import com.myschool.school.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class WishListController {

    @Autowired
    WishListService wishListService;

    @Autowired
    AuthenticationTokenService authenticationTokenService;
@PostMapping("/addToWishList")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product, @RequestParam("token") String token){
        authenticationTokenService.authenticateToken(token);

        Users users = authenticationTokenService.getUsers(token);
        WishList wishList = new WishList(users,product);

        wishListService.createWishlist(wishList);

        ApiResponse apiResponse = new ApiResponse(true,"added to wish list");

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/token")

    public ResponseEntity<List<ProductDto>>  getWishList(@PathVariable("token") String token){
    authenticationTokenService.authenticateToken(token);
    Users users = authenticationTokenService.getUsers(token);
    return (ResponseEntity<List<ProductDto>>) wishListService.getWishList(users);

    }
}
