package com.example.cartcheckoutservice.dtos;

import com.example.cartcheckoutservice.models.Cart;
import com.example.cartcheckoutservice.models.CartItem;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartDto {
    private long userId;
    private long cartId;
    private List<CartItemDto> items;

    public static CartDto getCartDto(Cart cart){
        CartDto cartDto=new CartDto();
        cartDto.setCartId(cart.getId());
        cartDto.setUserId(cart.getUserId());
        List<CartItemDto> cartItems=new ArrayList<>();
        for (CartItem cartItem:cart.getCartItems()){
            CartItemDto cartItemDto=new CartItemDto();
            cartItemDto.setQuantity(cartItem.getQuantity());
            cartItemDto.setProductId(cartItem.getProductId());
            cartItemDto.setUnitPrice(cartItem.getUnitPrice());
            cartItemDto.setItemId(cartItem.getId());
            cartItems.add(cartItemDto);
        }
        cartDto.setItems(cartItems);
        return cartDto;
    }
}
