package io.filluk.food.service;

import io.filluk.food.entity.Ingredient;
import io.filluk.food.entity.Meal;
import io.filluk.food.model.Cart;
import io.filluk.food.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    Cart cart;

    @Autowired
    public CartServiceImpl(Cart cart) {
        this.cart = cart;
    }

    @Override
    public void addToCart(Meal meal){
        cart.addItem(meal,1);
    }

    @Override
    public void removeFromCart(Meal meal) {
        cart.removeItem(meal);
    }

    @Override
    public List<CartItem> getCartItems() {
        return cart.getCartItems();
    }

    @Override
    public Cart getCart(){
        return this.cart;
    }

    @Override
    public List<Ingredient> getShoppingList() {
        return null;
    }
}
