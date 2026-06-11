package com.example.e_commerce.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Product {

    @Id
    private int productId;

    private String name;
    private String description;
    private double price;
    private String category;

    public Product() {}

    //Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // CATEGORY GETTERS/SETTERS
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}