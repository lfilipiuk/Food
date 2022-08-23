package io.filluk.food.service;

import io.filluk.food.entity.Meal;
import io.filluk.food.entity.Nutrient;

import java.util.List;
import java.util.Map;

public interface MealService {
    Map<Nutrient, Double> calculateNutrients(Meal meal);

    List<Meal> getMeals();

    void addMeal(Meal meal);

    void deleteMeal(Meal meal);

    Meal findMealById(long id);
}
