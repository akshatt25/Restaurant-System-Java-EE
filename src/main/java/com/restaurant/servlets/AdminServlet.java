package com.restaurant.servlets;


import com.restaurant.dao.CartDAO;
import com.restaurant.dao.OrderDAO;
import com.restaurant.models.CartItem;
import com.restaurant.models.Order;
import com.restaurant.models.OrderItem;
import com.restaurant.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;



import com.restaurant.dao.CartDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String menuItem = request.getParameter("menuItem");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String image = ""; // Set image path here
        String timestamp = ""; // Set timestamp here

        CartDAO cartDAO = new CartDAO();
        int userId = 1; // You need to get userId for admin
        int menuItemId = 0; // You need to get menuItemId based on menuItem and category

        boolean addedToCart = cartDAO.addToCart(userId, menuItemId, quantity, menuItem, price, image);
        if (addedToCart) {
            response.sendRedirect("admin.jsp"); // Redirect back to admin page after adding to cart
        } else {
            // Handle error
        }
    }
}
