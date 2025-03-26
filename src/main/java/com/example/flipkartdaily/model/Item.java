package com.example.flipkartdaily.model;

import java.util.Objects;

public class Item {
    private String category;
    private String brand;

    // Constructor
    public Item(String category, String brand) {
        this.category = category;
        this.brand = brand;
    }

    // Getters
    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    // Override equals and hashCode for using Item as a map key
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(category, item.category) &&
                Objects.equals(brand, item.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, brand);
    }

    @Override
    public String toString() {
        return "Item{" +
                "category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
