package com.restaurant.models;

public class OrderItem {
    private int itemId;
    private int orderId;
    private String itemName;
    private int quantity;
    private double price;

    public OrderItem(int itemId, int orderId, String itemName, int quantity, double price) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and setters

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
