package io.filluk.food.controller;

import io.filluk.food.entity.Ingredient;
import io.filluk.food.entity.Meal;
import io.filluk.food.repository.MealRepository;
import io.filluk.food.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("meals")
public class MealController {

    private final MealRepository mealRepository;
    private final ProductRepository productRepository;

    @Autowired
    public MealController(MealRepository mealRepository, ProductRepository productRepository) {
        this.mealRepository = mealRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/index")
    public String showMealList(Model model) {
        model.addAttribute("meals", mealRepository.findAll());
        return "meals-index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Meal meal, Model model){
        model.addAttribute("products", productRepository.findAll());
        Integer ingredientCount = meal.getIngredientCount();
        for (int i = 0; i < ingredientCount; i++) {
            meal.addIngredient(new Ingredient());
        }

        return "add-meal";
    }

    @PostMapping("/addmeal")
    public String addMeal(@Validated Meal meal, BindingResult result, Model model){

        System.out.println("=================");
        System.out.println(meal.getName());
        System.out.println(meal.getIngredients().size());

        for (Ingredient ingredient: meal.getIngredients()) {
            System.out.println(ingredient);
        }

        if(result.hasErrors()){
            System.out.println("ERROR");
            return "add-meal";
        }

        mealRepository.save(meal);
        return "redirect:/meals/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        model.addAttribute("products", productRepository.findAll());

        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        meal.addIngredient(new Ingredient());

        model.addAttribute("meal", meal);
        return "update-meal";
    }

    @PostMapping("/update/{id}")
    public String updateMeal(@PathVariable("id") long id, @Validated Meal meal,
                                BindingResult result, Model model){
        if(result.hasErrors()){
            meal.setId(id);
            return "update-meal";
        }

        mealRepository.save(meal);
        return "redirect:/meals/index";
    }



}
