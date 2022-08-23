package io.filluk.food.service;

import io.filluk.food.entity.Meal;
import io.filluk.food.repository.MealRepository;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MealServiceTest {

    @Mock
    MealRepository mealRepository;

    @Test
    public void when_save_meal_it_should_return_meal(){
        when(mealRepository.save(any(Meal.class))).thenReturn(new Meal());
    }
}
