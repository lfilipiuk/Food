package io.filluk.food.model;

import io.filluk.food.entity.Meal;

public class CartItem {
    private Meal meal;
    private int quantity;

    public CartItem() {
        this.quantity = 0;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
