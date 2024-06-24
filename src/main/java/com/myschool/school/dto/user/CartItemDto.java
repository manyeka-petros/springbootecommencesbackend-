package com.myschool.school.dto.user;

import com.myschool.school.model.Cart;
import com.myschool.school.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private  Long id;
    private Integer quantity;
    private Product product;

    public CartItemDto(Cart cart) {
        this.id = id;
        this.quantity = getQuantity();
        this.product = new Product();
    }
}
