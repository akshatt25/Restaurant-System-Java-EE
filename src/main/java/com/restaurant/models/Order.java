package com.restaurant.models;

import java.util.Date;
import java.util.List;

public class Order {
    private int orderId;
    private int userId;
    private String customerContact;
    private double amount;
    private String items;
    private String instructions;
    private Date timestamp;
    private List<OrderItem> orderItems;

    public Order(int orderId, int userId, String customerContact, double amount, String items, String instructions, Date timestamp, List<OrderItem> orderItems) {
        this.orderId = orderId;
        this.userId = userId;
        this.customerContact = customerContact;
        this.amount = amount;
        this.items = items;
        this.instructions = instructions;
        this.timestamp = timestamp;
        this.orderItems = orderItems;
    }

    // Getters and setters



	public Order(int userId, String customerContact, double amount, String items, String instructions) {
		
        this.userId = userId;
        this.customerContact = customerContact;
        this.amount = amount;
        this.items = items;
        this.instructions = instructions;
		
	}

	public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
