package finki.ukim.mk.webapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {
    private String name;
    private Double price;
    private Integer quantity;
    @ManyToOne
    private Category category;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Manufacturer manufacturer;

    public Product(String name, Double price, Integer quantity, Category category,Manufacturer manufacturer) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.id=(long)(Math.random()*1000);
        this.manufacturer=manufacturer;
    }


    public Product() {

    }
}
