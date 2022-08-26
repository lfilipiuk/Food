//package io.filluk.food.controller.web;
//
//import io.filluk.food.entity.Ingredient;
//import io.filluk.food.entity.Meal;
//import io.filluk.food.entity.Nutrient;
//import io.filluk.food.service.MealService;
//import io.filluk.food.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("meals")
//public class MealController {
//    private final ProductService productService;
//
//    private final MealService mealService;
//
//
//
//    @Autowired
//    public MealController(ProductService productService, MealService mealService) {
//        this.productService = productService;
//        this.mealService = mealService;
//    }
//
//    @GetMapping("/index")
//    public String showMealList(Model model) {
//        model.addAttribute("meals", mealService.getMeals());
//        return "meals-index";
//    }
//
//    @GetMapping("/newmeal")
//    public String showSignUpForm(Model model){
//        model.addAttribute("products", productService.getProducts());
//        model.addAttribute("meal", meal);
//        model.addAttribute("ingredients", meal.getIngredients());
//
//        return "add-meal";
//    }
//
//    @PostMapping("/newmeal")
//    public String addMeal(@Validated Meal meal, BindingResult result, Model model){
//        if(result.hasErrors()) {
//            return "add-meal";
//        }
//
//        mealService.addMeal(meal);
//        return "redirect:/meals/index";
//    }
//
//
//    @GetMapping("/edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model){
//        model.addAttribute("products", productService.getProducts());
//
//        Meal meal = mealService.findMealById(id);
//
//        model.addAttribute("meal", meal);
//
//        return "update-meal";
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateMeal(@PathVariable("id") long id, @Validated Meal meal,
//                                BindingResult result, Model model){
//        if(result.hasErrors()){
//            meal.setId(id);
//            return "update-meal";
//        }
//
//        mealService.addMeal(meal);
//        return "redirect:/meals/index";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteMeal(@PathVariable("id") long id, Model model){
//
//        Meal meal = mealService.findMealById(id);
//        mealService.deleteMeal(meal);
//
//        return "redirect:/products/index";
//    }
//
//    @GetMapping("/details/{id}")
//    public String detailsMeal(@PathVariable("id") long id, Model model){
//        Meal meal = mealService.findMealById(id);
//
//        Map<Nutrient, Double> nutrients = mealService.calculateNutrients(meal);
//
//        List<Ingredient> ingredients =
//        meal.getIngredients().stream()
//                        .filter(ingredient -> Objects.nonNull(ingredient))
//                        .filter(ingredient -> Objects.nonNull(ingredient.getProduct()))
//                        .collect(Collectors.toList());
//
//        meal.setIngredients(ingredients);
//
//        model.addAttribute("nutrients", nutrients);
//        model.addAttribute("meal", meal);
//
//        return "details-meal";
//    }
//
//
//}
