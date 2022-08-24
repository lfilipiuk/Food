package io.filluk.food.service;

import io.filluk.food.entity.Product;
import io.filluk.food.exceptions.ProductNotFoundException;
import io.filluk.food.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getProducts() {
        Iterable<Product> iterable = productRepository.findAll();

        return StreamSupport.stream(iterable.spliterator(), false)
                    .collect(Collectors.toList());
    }

    @Override
    public Product addProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public void deleteProductById(long id) {
        Product product = this.findProductById(id);
        productRepository.delete(product);
    }

    @Override
    public Product findProductById(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }
}
