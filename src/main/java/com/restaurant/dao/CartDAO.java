package com.restaurant.dao;

import com.restaurant.models.CartItem;
import com.restaurant.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    // Method to add an item to the cart
    public boolean addToCart(int userId, int menuItemId, int quantity, String name, double price, String image) {
        String sql = "INSERT INTO cart (user_id, menu_item_id, quantity, name, price, image) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, menuItemId);
            pstmt.setInt(3, quantity);
            pstmt.setString(4, name);
            pstmt.setDouble(5, price);
            pstmt.setString(6, image);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to remove an item from the cart
    public boolean removeFromCart(int cartItemId) {
        String sql = "DELETE FROM cart WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cartItemId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to increase the quantity of a cart item
    public boolean increaseCartItemQuantity(int cartItemId) {
        String sql = "UPDATE cart SET quantity = quantity + 1 WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cartItemId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to decrease the quantity of a cart item
    public boolean decreaseCartItemQuantity(int cartItemId) {
        String sql = "UPDATE cart SET quantity = quantity - 1 WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, cartItemId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get all cart items for a user
    public List<CartItem> getCartItemsByUserId(int userId) {
        List<CartItem> cartItems = new ArrayList<>();
        String sql = "SELECT * FROM cart WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int menuItemId = rs.getInt("menu_item_id");
                int quantity = rs.getInt("quantity");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                String timestamp = rs.getString("timestamp");
                cartItems.add(new CartItem(id, userId, menuItemId, quantity, name, price, image, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartItems;
    }
    public boolean clearCart(int userId) {
        String sql = "DELETE FROM cart WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
