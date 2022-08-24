package io.filluk.food.service;

import io.filluk.food.entity.Ingredient;
import io.filluk.food.entity.Meal;
import io.filluk.food.model.Cart;
import io.filluk.food.model.CartItem;

import java.util.List;

public interface CartService {
    void addToCart(Meal meal);

    void removeFromCart(Meal meal);

    List<CartItem> getCartItems();

    Cart getCart();

    List<Ingredient> getShoppingList();
}
