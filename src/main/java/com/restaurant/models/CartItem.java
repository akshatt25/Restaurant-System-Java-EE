package com.restaurant.models;

public class CartItem {
    private int id;
    private int userId;
    private int menuItemId;
    private int quantity;
    private String name;
    private double price;
    private String image;
    private String timestamp;

    public CartItem() {
    }

    public CartItem(int id, int userId, int menuItemId, int quantity, String name, double price, String image, String timestamp) {
        this.id = id;
        this.userId = userId;
        this.menuItemId = menuItemId;
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.image = image;
        this.timestamp = timestamp;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
