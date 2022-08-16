package io.filluk.food.controller;

import io.filluk.food.entity.Meal;
import io.filluk.food.repository.MealRepository;
import io.filluk.food.service.CartService;
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
    private final MealRepository mealRepository;

    @Autowired
    public CartController(CartService cartService, MealRepository mealRepository) {
        this.cartService = cartService;
        this.mealRepository = mealRepository;
    }


    @GetMapping("/")
    public String showCart(Model model){
        return "cart-index";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable("id") long id, Model model){
        Meal meal = mealRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Invalud meal Id:" + id));
        cartService.addToCart(meal);

        return "meals-index";
    }

    @GetMapping("/remove")
    public String removeFromCart(Model model){
        Meal meal = (Meal) model.getAttribute("meal");
        cartService.removeFromCart(meal);

        return "meals-index";
    }

}
