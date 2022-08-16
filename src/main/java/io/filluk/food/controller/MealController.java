package io.filluk.food.controller;

import io.filluk.food.entity.Ingredient;
import io.filluk.food.entity.Meal;
import io.filluk.food.entity.Nutrient;
import io.filluk.food.repository.MealRepository;
import io.filluk.food.repository.ProductRepository;
import io.filluk.food.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("meals")
public class MealController {

    private final MealRepository mealRepository;
    private final ProductRepository productRepository;

    private final MealService mealService;

    @Autowired
    Meal meal;


    @Autowired
    public MealController(MealRepository mealRepository, ProductRepository productRepository,
        MealService mealService) {
        this.mealRepository = mealRepository;
        this.productRepository = productRepository;
        this.mealService = mealService;
    }

    @GetMapping("/index")
    public String showMealList(Model model) {
        model.addAttribute("meals", mealRepository.findAll());
        return "meals-index";
    }

    @GetMapping("/newmeal")
    public String showSignUpForm(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("meal", meal);
        model.addAttribute("ingredients", meal.getIngredients());

        return "add-meal";
    }

    @PostMapping("/newmeal")
    public String addMeal(@Validated Meal meal, BindingResult result, Model model){
        if(result.hasErrors()) {
            return "add-meal";
        }

        mealRepository.save(meal);
        return "redirect:/meals/index";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model){
        model.addAttribute("products", productRepository.findAll());

        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid meal Id:" + id));

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

    @GetMapping("/delete/{id}")
    public String deleteMeal(@PathVariable("id") long id, Model model){
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:"+id));
        mealRepository.delete(meal);
        return "redirect:/products/index";
    }

    @GetMapping("/details/{id}")
    public String detailsMeal(@PathVariable("id") long id, Model model){
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:"+id));


        Map<Nutrient, Double> nutrients = mealService.calculateNutrients(meal);

        //service layer
        List<Ingredient> ingredients =
        meal.getIngredients().stream()
                        .filter(ingredient -> Objects.nonNull(ingredient))
                        .filter(ingredient -> Objects.nonNull(ingredient.getProduct()))
                        .collect(Collectors.toList());
        meal.setIngredients(ingredients);

        model.addAttribute("nutrients", nutrients);
        model.addAttribute("meal", meal);

        return "details-meal";
    }


}
