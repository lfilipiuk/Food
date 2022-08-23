package io.filluk.food.configuration;

import io.filluk.food.entity.Meal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class MyConfiguration {

    @Bean
    @SessionScope
    public Meal meal(){
        return new Meal();
    }

}
