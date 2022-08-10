package io.filluk.food.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "meals")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "recipe")
    String recipe;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_id")
    private List<Ingredient> ingredients = new ArrayList<>();

    public Integer getIngredientCount() {
        return ingredientCount;
    }

    public void setIngredientCount(Integer ingredientCount) {
        this.ingredientCount = ingredientCount;
    }
    @Transient
    Integer ingredientCount;

    public Meal() {
        this.name = "";
        this.ingredientCount = 1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.ingredientCount = ingredients.size()+1;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingredients.add(ingredient);
        this.ingredientCount++;
    }

    public void add(Ingredient ingredient){
        if(ingredients == null){
            ingredients = new ArrayList<>();
        }
        ingredients.add(ingredient);
        ingredientCount++;
    }
}
