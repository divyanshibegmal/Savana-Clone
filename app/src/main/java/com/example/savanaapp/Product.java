package com.example.savanaapp;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private String category;
    private double price;
    private double originalPrice;
    private int imageResId;
    private String description;
    private float rating;
    private int reviewCount;
    private int quantity = 1;

    public Product(int id, String name, String category, double price, double originalPrice, int imageResId, String description, float rating, int reviewCount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.originalPrice = originalPrice;
        this.imageResId = imageResId;
        this.description = description;
        this.rating = rating;
        this.reviewCount = reviewCount;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public double getOriginalPrice() { return originalPrice; }
    public int getImageResId() { return imageResId; }
    public String getDescription() { return description; }
    public float getRating() { return rating; }
    public int getReviewCount() { return reviewCount; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getDiscountPercent() {
        if (originalPrice > price) {
            return (int) (((originalPrice - price) / originalPrice) * 100);
        }
        return 0;
    }
}
