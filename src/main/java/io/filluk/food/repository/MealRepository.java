package io.filluk.food.repository;

import io.filluk.food.entity.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Long> {
}
