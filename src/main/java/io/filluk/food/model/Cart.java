package io.filluk.food.model;

import io.filluk.food.entity.Meal;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    public Cart() {
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    private CartItem findCartItemById(Long id){
        for(CartItem item : this.cartItems){
            if(item.getMeal().getId().equals(id)){
                return item;
            }
        }
        return null;
    }

    public void addItem(Meal meal, int quantity){
        CartItem item = this.findCartItemById(meal.getId());

        if(item == null){
            item = new CartItem();
            item.setMeal(meal);
            this.cartItems.add(item);
        }

        int newQuantity = item.getQuantity() + quantity;

        if(quantity <= 0){
            this.cartItems.remove(item);
        }else{
            item.setQuantity(newQuantity);
        }
    }

    public void updateItem(Long id, int quantity){
        CartItem item = this.findCartItemById(id);
        if(item != null){
            if(quantity <= 0){
                this.cartItems.remove(item);
            }else{
                item.setQuantity(quantity);
            }
        }
    }

    public void removeItem(Meal meal){
        CartItem item = this.findCartItemById(meal.getId());
        if(item != null){
            this.cartItems.remove(item);
        }
    }


}
