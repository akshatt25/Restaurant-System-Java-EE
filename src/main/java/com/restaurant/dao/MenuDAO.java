package com.restaurant.dao;

import com.restaurant.models.MenuItem;
import com.restaurant.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM menu";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setId(rs.getInt("id"));
                    menuItem.setName(rs.getString("name"));
                    menuItem.setCategory(rs.getString("category"));
                    menuItem.setDescription(rs.getString("description"));
                    menuItem.setPrice(rs.getDouble("price"));
                    menuItem.setImage(rs.getString("image"));
                    menuItems.add(menuItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    public List<MenuItem> getMenuItemsByCategory(String category) {
        List<MenuItem> menuItems = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM menu WHERE category = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, category);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    MenuItem menuItem = new MenuItem();
                    menuItem.setId(rs.getInt("id"));
                    menuItem.setName(rs.getString("name"));
                    menuItem.setCategory(rs.getString("category"));
                    menuItem.setDescription(rs.getString("description"));
                    menuItem.setPrice(rs.getDouble("price"));
                    menuItem.setImage(rs.getString("image"));
                    menuItems.add(menuItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }
    public MenuItem getMenuItemById(int id) {
        String sql = "SELECT * FROM menu WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String category = rs.getString("category");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                return new MenuItem(id, name, category, description, price, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
