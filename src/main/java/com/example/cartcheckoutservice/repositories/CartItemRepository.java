package com.example.cartcheckoutservice.repositories;

import com.example.cartcheckoutservice.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
