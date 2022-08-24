package io.filluk.food.controller.rest;

import io.filluk.food.assembler.ProductModelAssembler;
import io.filluk.food.entity.Product;
import io.filluk.food.service.ProductService;
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
public class ProductRESTController {

    private final ProductService productService;
    private final ProductModelAssembler assembler;

    @Autowired
    public ProductRESTController(ProductService productService, ProductModelAssembler assembler) {
        this.productService = productService;
        this.assembler = assembler;
    }

    @GetMapping("/products")
    public CollectionModel<EntityModel<Product>> all(){

        List<EntityModel<Product>> products = productService.getProducts().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(products,
                linkTo(methodOn(ProductRESTController.class).all()).withSelfRel());
    }

    @PostMapping("/products")
    ResponseEntity<?> newProduct(@RequestBody Product newProduct){
        EntityModel<Product> entityModel = assembler.toModel(productService.addProduct(newProduct));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/products/{id}")
    public EntityModel<Product> one(@PathVariable Long id){
        Product product = productService.findProductById(id);

        return assembler.toModel(product);

    }

    @PutMapping("/products/{id}")
    ResponseEntity<?> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        Product updatedProduct = productService.updateProduct(id, newProduct);

        EntityModel<Product> entityModel = assembler.toModel(updatedProduct);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/products/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }
}
