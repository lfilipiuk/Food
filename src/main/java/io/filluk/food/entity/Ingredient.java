package io.filluk.food.entity;


import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "serving", nullable = true)
    Serving serving;

    @Column(name = "quantity", nullable = true)
    Double quantity;

    public Ingredient() {
        this.product = new Product();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Serving getServing() {
        return serving;
    }

    public void setServing(Serving serving) {
        this.serving = serving;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
    //            ", meal=" + meal.getName() +
                ", product=" + product +
                ", serving=" + serving +
                ", quantity=" + quantity +
                '}';
    }
}
