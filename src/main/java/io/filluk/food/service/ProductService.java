package io.filluk.food.service;

import io.filluk.food.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();

    Product addProduct(Product product);

    void deleteProduct(Product product);

    void deleteProductById(long id);

    Product findProductById(long id);

    Product updateProduct(Long id, Product product);
}
