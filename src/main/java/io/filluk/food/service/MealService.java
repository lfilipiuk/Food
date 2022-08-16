package io.filluk.food.service;

import io.filluk.food.entity.Ingredient;
import io.filluk.food.entity.Meal;
import io.filluk.food.entity.Nutrient;
import io.filluk.food.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MealService {

    Map<Nutrient,Double> nutrients;

    public MealService() {

    }

    public Map<Nutrient, Double> calculateNutrients(Meal meal){
        nutrients = new HashMap<>();
        nutrients.put(Nutrient.CALORIES,0.0);
        nutrients.put(Nutrient.CARBOHYDRATES,0.0);
        nutrients.put(Nutrient.SUGARS,0.0);
        nutrients.put(Nutrient.PROTEIN,0.0);
        nutrients.put(Nutrient.FAT,0.0);

        if(meal == null)
            return null;

        for (Ingredient ingredient : meal.getIngredients()){
            if(ingredient.getProduct() != null) {
                nutrients.put(Nutrient.CALORIES, nutrients.get(Nutrient.CALORIES) + ingredient.getProduct().getCalories());
                nutrients.put(Nutrient.CARBOHYDRATES, nutrients.get(Nutrient.CARBOHYDRATES) + ingredient.getProduct().getCarbohydrates());
                nutrients.put(Nutrient.SUGARS, nutrients.get(Nutrient.SUGARS) + ingredient.getProduct().getSugars());
                nutrients.put(Nutrient.PROTEIN, nutrients.get(Nutrient.PROTEIN) + ingredient.getProduct().getProtein());
                nutrients.put(Nutrient.FAT, nutrients.get(Nutrient.FAT) + ingredient.getProduct().getFat());
            }
        }

        return nutrients;
    }

    public void trimIngredients(Meal meal){
        for(Ingredient ingredient : meal.getIngredients()){
            if(ingredient.getProduct() == null){
                meal.getIngredients().remove(ingredient);
            }
        }
    }



}
