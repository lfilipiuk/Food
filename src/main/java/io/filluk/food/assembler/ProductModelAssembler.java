package io.filluk.food.assembler;

import io.filluk.food.controller.rest.ProductRESTController;
import io.filluk.food.entity.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public
class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    @Override
    public EntityModel<Product> toModel(Product product){
        return EntityModel.of(product,
                linkTo(methodOn(ProductRESTController.class).one(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductRESTController.class).all()).withRel("products"));
    }
}
