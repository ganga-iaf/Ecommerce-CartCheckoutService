package com.example.cartcheckoutservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
   private long itemId;
   private long productId;
   private int quantity;
   private double unitPrice;
}
