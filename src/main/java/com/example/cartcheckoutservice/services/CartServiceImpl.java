package com.example.cartcheckoutservice.services;

import com.example.cartcheckoutservice.dtos.CartItemDto;
import com.example.cartcheckoutservice.exceptions.ResourceNotFoundException;
import com.example.cartcheckoutservice.models.Cart;
import com.example.cartcheckoutservice.models.CartItem;
import com.example.cartcheckoutservice.repositories.CartItemRepository;
import com.example.cartcheckoutservice.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCartByUserId(long user_id) {
        return cartRepository.findById(user_id).orElseGet(()->{
            Cart newCart = new Cart();
            newCart.setUserId(user_id);
            return cartRepository.save(newCart);
        });
    }


    @Override
    public Cart addCartItem(CartItemDto cartItemDto, long user_id) {
        Cart cart = cartRepository.findById(user_id).orElseGet(()->{
            Cart newCart = new Cart();
            newCart.setUserId(user_id);
            return cartRepository.save(newCart);
        });
        if(cart.getCartItems()==null){
            cart.setCartItems(new ArrayList<>());
        }
        List<CartItem> cartItems=cart.getCartItems();
        Optional<CartItem> cartItemOptional =cartItems.stream().filter(cartItem -> cartItem.getProductId().equals(cartItemDto.getProductId())).findAny();
        CartItem cartItem;
        if(cartItemOptional.isPresent()){
            cartItem = cartItemOptional.get();
            cartItem.setQuantity(cartItemDto.getQuantity()+cartItem.getQuantity());
        }else {
            cartItem = new CartItem();
            cartItem.setCart(cart);

            cartItem.setProductId(cartItemDto.getProductId());
            cartItem.setUnitPrice(cartItemDto.getUnitPrice());
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem=cartItemRepository.save(cartItem);
            cart.addCartItem(cartItem);
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart removeCartItem(long cartItemId, long user_id) throws ResourceNotFoundException{
         cartItemRepository.deleteById(cartItemId);
         return cartRepository.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("Cart not found"));
    }

    @Override
    public Cart updateCartItem(int quantity,long cartItemId,long user_id) throws ResourceNotFoundException {
        CartItem cartItem=cartItemRepository.findById(cartItemId).orElseThrow(()-> new ResourceNotFoundException("Cart item not found"));
           cartItem.setQuantity(quantity);
           cartItemRepository.save(cartItem);
        return cartRepository.findById(user_id).orElseThrow(()->new ResourceNotFoundException("Cart not found"));
    }

    @Override
    public Cart clearCart(long user_id) throws ResourceNotFoundException {
        Cart cart = cartRepository.findById(user_id).orElseThrow(()-> new ResourceNotFoundException("Cart not found"));
            cartItemRepository.deleteAll(cart.getCartItems());
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }
}
