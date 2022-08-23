package io.filluk.food.service;

import io.filluk.food.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    void addProduct(Product product);

    void deleteProduct(Product product);

    Product findProductById(long id);
}
