package com.example.cartcheckoutservice.controllers;

import com.example.cartcheckoutservice.dtos.CartDto;
import com.example.cartcheckoutservice.dtos.CartItemDto;
import com.example.cartcheckoutservice.exceptions.ResourceNotFoundException;
import com.example.cartcheckoutservice.exceptions.TokenMissingException;
import com.example.cartcheckoutservice.models.Cart;
import com.example.cartcheckoutservice.services.CartService;
import com.example.cartcheckoutservice.utils.JwtTokenValidator;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private JwtTokenValidator jwtTokenValidator;


    @GetMapping()
    public ResponseEntity<CartDto> getCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws TokenMissingException {
        long userId=validateToken(token);
        Cart cart=cartService.getCartByUserId(userId);
        return new ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<CartDto> addItem(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody CartItemDto cartItemDto) throws TokenMissingException {
        long userId=validateToken(token);
        Cart cart=cartService.addCartItem(cartItemDto,userId);
        return new ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }

    @PutMapping("/item/{itemId}/quantity/{quantity}")
    public ResponseEntity<CartDto> updateQuantity(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,@PathVariable long itemId,@PathVariable int quantity) throws ResourceNotFoundException,TokenMissingException {
        long userId=validateToken(token);
        Cart cart=cartService.updateCartItem(quantity,itemId,userId);
       return new ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }

    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<CartDto> deleteCartItem(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable long itemId) throws ResourceNotFoundException,TokenMissingException {
        long userId=validateToken(token);
        Cart cart=cartService.removeCartItem(itemId,userId);
         return new  ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<CartDto> clearCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) throws ResourceNotFoundException,TokenMissingException {
        long userId=validateToken(token);
        Cart cart=cartService.clearCart(userId);
        return new  ResponseEntity<>(CartDto.getCartDto(cart), HttpStatus.OK);
    }

    private long validateToken(String token) throws TokenMissingException {
        if(token == null || token.isEmpty()){
            throw new TokenMissingException("token is missing");
        }
        return jwtTokenValidator.validateAndGetUserId(token);
    }

}
