package com.example.cartcheckoutservice.services;

import com.example.cartcheckoutservice.dtos.CartItemDto;
import com.example.cartcheckoutservice.exceptions.ResourceNotFoundException;
import com.example.cartcheckoutservice.models.Cart;
import com.example.cartcheckoutservice.models.CartItem;

public interface CartService {
    Cart getCartByUserId(long user_id);
    Cart addCartItem(CartItemDto cartItem, long user_id);
    Cart removeCartItem(long cartItemId,long user_id) throws ResourceNotFoundException;
    Cart updateCartItem(int quantity,long cartItemId,long user_id) throws ResourceNotFoundException;
    Cart clearCart(long user_id) throws ResourceNotFoundException;
}
