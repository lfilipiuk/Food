package io.filluk.food.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@SecondaryTable(name = "nutrients", pkJoinColumns = @PrimaryKeyJoinColumn(name = "product_id"))
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "carbohydrates", table = "nutrients")
    Double carbohydrates;

    @Column(name = "sugars", table = "nutrients")
    Double sugars;

    @Column(name = "calories", table = "nutrients")
    Double calories;

    @Column(name = "protein", table = "nutrients")
    Double protein;

    @Column(name = "fat", table = "nutrients")
    Double fat;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    List<Ingredient> ingredients;

    public Product(String name) {
        this.name = name;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Double getSugars() {
        return sugars;
    }

    public void setSugars(Double sugars) {
        this.sugars = sugars;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", carbohydrates=" + carbohydrates +
                ", sugars=" + sugars +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                '}';
    }
}
