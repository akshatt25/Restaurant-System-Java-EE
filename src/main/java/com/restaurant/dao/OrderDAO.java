package com.restaurant.dao;

import com.restaurant.models.Order;
import com.restaurant.models.OrderItem;
import com.restaurant.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    // Add Order method with items
    public boolean addOrder(Order order, List<OrderItem> items) {
        String sqlOrder = "INSERT INTO orders (user_id, customer_contact, amount, items, instructions) VALUES (?, ?, ?, ?, ?)";
        String sqlOrderItem = "INSERT INTO order_items (order_id, item_name, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmtOrder = conn.prepareStatement(sqlOrder, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement pstmtOrderItem = conn.prepareStatement(sqlOrderItem)) {

            // Insert order
            pstmtOrder.setInt(1, order.getUserId());
            pstmtOrder.setString(2, order.getCustomerContact());
            pstmtOrder.setDouble(3, order.getAmount());
            pstmtOrder.setString(4, order.getItems());
            pstmtOrder.setString(5, order.getInstructions());
            int rowsAffected = pstmtOrder.executeUpdate();

            // Get generated order ID
            ResultSet rs = pstmtOrder.getGeneratedKeys();
            if (rs.next()) {
                int orderId = rs.getInt(1);

                // Insert order items
                for (OrderItem item : items) {
                    pstmtOrderItem.setInt(1, orderId);
                    pstmtOrderItem.setString(2, item.getItemName());
                    pstmtOrderItem.setInt(3, item.getQuantity());
                    pstmtOrderItem.setDouble(4, item.getPrice());
                    pstmtOrderItem.addBatch();
                }
                pstmtOrderItem.executeBatch();
            }

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get orders by user ID method
    public List<Order> getOrdersByUserId(int userId) {
        List<Order> orders = new ArrayList<>();
        String sqlOrder = "SELECT * FROM orders WHERE user_id = ?";
        String sqlOrderItem = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmtOrder = conn.prepareStatement(sqlOrder);
             PreparedStatement pstmtOrderItem = conn.prepareStatement(sqlOrderItem)) {

            pstmtOrder.setInt(1, userId);
            ResultSet rsOrder = pstmtOrder.executeQuery();

            while (rsOrder.next()) {
                int orderId = rsOrder.getInt("order_id");
                String customerContact = rsOrder.getString("customer_contact");
                double amount = rsOrder.getDouble("amount");
                String items = rsOrder.getString("items");
                String instructions = rsOrder.getString("instructions");
                Date timestamp = rsOrder.getTimestamp("timestamp");

                // Get order items
                List<OrderItem> orderItems = new ArrayList<>();
                pstmtOrderItem.setInt(1, orderId);
                ResultSet rsOrderItem = pstmtOrderItem.executeQuery();
                while (rsOrderItem.next()) {
                    int itemId = rsOrderItem.getInt("item_id");
                    String itemName = rsOrderItem.getString("item_name");
                    int quantity = rsOrderItem.getInt("quantity");
                    double price = rsOrderItem.getDouble("price");
                    orderItems.add(new OrderItem(itemId, orderId, itemName, quantity, price));
                }

                orders.add(new Order(orderId, userId, customerContact, amount, items, instructions, timestamp, orderItems));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
