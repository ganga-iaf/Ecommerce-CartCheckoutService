package com.example.cartcheckoutservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Getter
@Setter
@Entity
public class Cart extends BaseModel {
    private long userId;
    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    public void addCartItem(CartItem cartItem){
        this.cartItems.add(cartItem);
    }
}
