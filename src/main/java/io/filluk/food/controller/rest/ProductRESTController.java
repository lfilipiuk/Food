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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/products")
    //@Operation(summary = "Get a list of all products")
    public CollectionModel<EntityModel<Product>> all(){

        List<EntityModel<Product>> products = productService.getProducts().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(products,
                linkTo(methodOn(ProductRESTController.class).all()).withSelfRel());
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/products")
    //@Operation(summary = "Create a new product")
    ResponseEntity<?> newProduct(@RequestBody Product newProduct){
        EntityModel<Product> entityModel = assembler.toModel(productService.addProduct(newProduct));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/products/{id}")
    //@Operation(summary = "Find a product by its id")
    public EntityModel<Product> one(@PathVariable Long id){
        Product product = productService.findProductById(id);

        return assembler.toModel(product);

    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/products/{id}")
    //@Operation(summary = "Update a product")
    ResponseEntity<?> updateProduct(@RequestBody Product newProduct, @PathVariable Long id){
        Product updatedProduct = productService.updateProduct(id, newProduct);

        EntityModel<Product> entityModel = assembler.toModel(updatedProduct);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/products/{id}")
    //@Operation(summary = "Delete a product")
    ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);

        return ResponseEntity.noContent().build();
    }
}
