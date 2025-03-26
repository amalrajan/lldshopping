package com.example.flipkartdaily.model;

public class InventoryEntry {
    private double price;
    private int quantity;

    public InventoryEntry(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryEntry{" +
                "price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
