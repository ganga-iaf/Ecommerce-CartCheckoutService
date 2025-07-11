package com.example.cartcheckoutservice.controllers;

import com.example.cartcheckoutservice.dtos.CartDto;
import com.example.cartcheckoutservice.dtos.CartItemDto;
import com.example.cartcheckoutservice.exceptions.ResourceNotFoundException;
import com.example.cartcheckoutservice.models.Cart;
import com.example.cartcheckoutservice.services.CartService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDto> getCart(@PathVariable long userId){
        Cart cart=cartService.getCartByUserId(userId);
        return new ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }



    @PostMapping("/user/{userId}/add")
    public ResponseEntity<CartDto> addItem(@RequestBody CartItemDto cartItemDto, @PathVariable long userId) {
        Cart cart=cartService.addCartItem(cartItemDto,userId);
        return new ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/item/{itemId}/quantity/{quantity}")
    public ResponseEntity<CartDto> updateQuantity(@PathVariable long userId,@PathVariable long itemId,@PathVariable int quantity) throws ResourceNotFoundException {
       Cart cart=cartService.updateCartItem(quantity,itemId,userId);
       return new ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/item/{itemId}")
    public ResponseEntity<CartDto> deleteCartItem(@PathVariable long userId, @PathVariable long itemId) throws ResourceNotFoundException {
         Cart cart=cartService.removeCartItem(itemId,userId);
         return new  ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<CartDto> clearCart(@PathVariable long userId) throws ResourceNotFoundException {
        Cart cart=cartService.clearCart(userId);
        return new  ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }


}
