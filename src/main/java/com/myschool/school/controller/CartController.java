package com.myschool.school.controller;

import com.myschool.school.common.ApiResponse;
import com.myschool.school.dto.user.AddCartDto;
import com.myschool.school.dto.user.CartDto;
import com.myschool.school.model.AuthenticationToken;
import com.myschool.school.model.Users;
import com.myschool.school.service.AuthenticationTokenService;
import com.myschool.school.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    private AuthenticationTokenService authenticationTokenService;

    @PostMapping("/addToCart")
    public ResponseEntity<ApiResponse > addtoCart(@RequestBody AddCartDto addCartDto, @RequestParam("token") String token){
        authenticationTokenService.authenticateToken(token);
        Users users = authenticationTokenService.getUsers(token);
        cartService.addToCart(addCartDto,users);

        return new ResponseEntity<>(new ApiResponse(true,"added to cart"), HttpStatus.OK);
    }

    @GetMapping("/getCart")

    public ResponseEntity<CartDto> getCartItem(@RequestParam("token") String token){
        authenticationTokenService.authenticateToken(token);
      Users users =  authenticationTokenService.getUsers(token);
        CartDto cartDto = cartService.showCart(users);
        return new ResponseEntity<>(cartDto,HttpStatus.OK);
    }
}
