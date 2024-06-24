package com.myschool.school.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCartDto {
    private  Long cartId;
    private Long productId;
    private  int  quantity;
}
