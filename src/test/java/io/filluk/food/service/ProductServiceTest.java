package io.filluk.food.service;

import io.filluk.food.entity.Product;
import io.filluk.food.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    private Product product;


    @Before
    public void setUp(){
        product = new Product();
        product.setName("Test Product");
        product.setSugars(1.0);
        product.setCarbohydrates(2.0);
        product.setProtein(3.0);
        product.setFat(4.0);
        product.setCalories(5.0);
    }

    @Test
    public void when_save_product_it_should_return_product(){
        when(productRepository.save(any(Product.class))).thenReturn(new Product());

        Product created = productService.addProduct(product);

        assertThat(created.getName()).isSameAs(product.getName());
    }

}
