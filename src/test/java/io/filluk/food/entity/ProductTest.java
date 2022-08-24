package io.filluk.food.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductTest {

    @Autowired
    private TestEntityManager entityManager;

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
    public void saveProduct() {
        Product savedProduct = this.entityManager.persistAndFlush(product);
        assertThat(savedProduct.getName()).isEqualTo("Test Product");
        assertThat(savedProduct.getId()).isNotNull();
    }
}
