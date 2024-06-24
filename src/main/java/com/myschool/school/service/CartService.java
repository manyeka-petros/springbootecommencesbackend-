package com.myschool.school.service;

import com.myschool.school.dto.user.AddCartDto;
import com.myschool.school.dto.user.CartDto;
import com.myschool.school.dto.user.CartItemDto;
import com.myschool.school.model.Cart;
import com.myschool.school.model.Product;
import com.myschool.school.model.Users;
import com.myschool.school.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CartService {

    @Autowired
  private   CartRepository cartRepository;

    @Autowired
    private  ProductService  productService;

    public void addToCart(AddCartDto addCartDto, Users users) {
       Product product =  productService.findById(addCartDto.getProductId());
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUsers(users);
        cart.setQuantity(addCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }

    public CartDto showCart(Users users) {

        List<Cart> carts = cartRepository.findAllByUsersOrderByCreatedDateDesc(users);
        List<CartItemDto> cartItemDtos = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart :carts){
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity()* cart.getProduct().getPrice();
            cartItemDtos.add(cartItemDto);
        }
        CartDto cartDto =  new CartDto();
        cartDto.setTotalAmount(totalCost);
        cartDto.setCartItemDto(cartItemDtos);
        return cartDto;
    }
}
