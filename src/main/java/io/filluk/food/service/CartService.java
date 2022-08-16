package io.filluk.food.service;

import io.filluk.food.entity.Meal;
import io.filluk.food.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    Cart cart = new Cart();

    public void addToCart(Meal meal){
        cart.addItem(meal,1);
    }

    public void removeFromCart(Meal meal) {
        cart.removeItem(meal);
    }


}
