package io.filluk.food.controller;

import io.filluk.food.entity.Meal;
import io.filluk.food.service.CartService;
import io.filluk.food.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cart")
public class CartController {
    private final CartService cartService;
    private final MealService mealService;


    @Autowired
    public CartController(CartService cartService, MealService mealService) {
        this.cartService = cartService;
        this.mealService = mealService;
    }

    @GetMapping("/")
    public String showCart(Model model){
        model.addAttribute("cart", cartService.getCartItems());
        model.addAttribute("cartItems", cartService.getCartItems());

        return "cart-index";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") long id, Model model){
        Meal meal = mealService.findMealById(id);
        cartService.addToCart(meal);

        model.addAttribute("cart", cartService.getCart());
        model.addAttribute("cartItems", cartService.getCartItems());

        return "cart-index";
    }

    @GetMapping("/remove")
    public String removeFromCart(Model model){
        Meal meal = (Meal) model.getAttribute("meal");
        cartService.removeFromCart(meal);

        model.addAttribute("cart", cartService.getCartItems());
        model.addAttribute("cartItems", cartService.getCartItems());

        return "cart-index";
    }

}
