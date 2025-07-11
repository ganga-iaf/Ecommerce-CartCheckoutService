package com.example.cartcheckoutservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem extends BaseModel {

    private Long productId;
    private int quantity;
    private double unitPrice;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
