package io.filluk.food.controller.rest;

import io.filluk.food.assembler.MealModelAssembler;
import io.filluk.food.entity.Meal;
import io.filluk.food.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("rest")
public class MealRESTController {

    private final MealService mealService;
    private final MealModelAssembler assembler;

    @Autowired
    public MealRESTController(MealService mealService, MealModelAssembler assembler) {
        this.mealService = mealService;
        this.assembler = assembler;
    }

    @GetMapping("/meals")
    public CollectionModel<EntityModel<Meal>> all(){

        List<EntityModel<Meal>> meals = mealService.getMeals().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(meals,
                linkTo(methodOn(MealRESTController.class).all()).withSelfRel());
    }

    @PostMapping("/meals")
    ResponseEntity<?> newMeal(@RequestBody Meal newMeal){
        EntityModel<Meal> entityModel = assembler.toModel(mealService.addMeal(newMeal));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/meals/{id}")
    public EntityModel<Meal> one(@PathVariable Long id){
        Meal meal = mealService.findMealById(id);

        return assembler.toModel(meal);

    }

    @DeleteMapping("/meals/{id}")
    ResponseEntity<?> deleteMeal(@PathVariable Long id){
        mealService.deleteMealById(id);

        return ResponseEntity.noContent().build();
    }
}
