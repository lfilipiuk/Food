package io.filluk.food.assembler;

import io.filluk.food.controller.rest.MealRESTController;
import io.filluk.food.entity.Meal;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MealModelAssembler implements RepresentationModelAssembler<Meal, EntityModel<Meal>> {
    @Override
    public EntityModel<Meal> toModel(Meal meal){
        return EntityModel.of(meal,
                linkTo(methodOn(MealRESTController.class).one(meal.getId())).withSelfRel(),
                linkTo(methodOn(MealRESTController.class).all()).withRel("meals"));
    }


}
