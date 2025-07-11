package com.example.cartcheckoutservice.repositories;

import com.example.cartcheckoutservice.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
