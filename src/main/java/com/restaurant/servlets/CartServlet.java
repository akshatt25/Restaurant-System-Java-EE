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

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CartDAO cartDAO = new CartDAO();

        if (user != null) {
            String action = request.getParameter("action");

            switch (action) {
                case "remove":
                    int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
                    cartDAO.removeFromCart(cartItemId);
                    response.getWriter().write("removed");
                    break;
                case "increaseQuantity":
                    int cartItemI = Integer.parseInt(request.getParameter("cartItemId"));
                    cartDAO.increaseCartItemQuantity(cartItemI);
                    response.getWriter().write("increased");
                    break;
                case "decreaseQuantity":
                    int cartItemD = Integer.parseInt(request.getParameter("cartItemId"));
                    cartDAO.decreaseCartItemQuantity(cartItemD);
                    response.getWriter().write("decreased");
                    break;
                case "checkout":
                    String contactNumber = request.getParameter("contactNumber");
                    String instructions = request.getParameter("instructions");
                    List<CartItem> cartItems = cartDAO.getCartItemsByUserId(user.getId());
                    if (cartItems != null && !cartItems.isEmpty()) {
                        double totalPrice = cartItems.stream()
                            .mapToDouble(item -> item.getQuantity() * item.getPrice())
                            .sum();
                        String items = cartItems.stream()
                            .map(item -> item.getName() + "-" + item.getQuantity())
                            .collect(Collectors.joining(", "));

                        // Create Order object
                        Order order = new Order(user.getId(), contactNumber, totalPrice, items, instructions);

                        // Create OrderItems
                        List<OrderItem> orderItems = cartItems.stream()
                            .map(item -> new OrderItem(0, order.getOrderId(), item.getName(), item.getQuantity(), item.getPrice()))
                            .collect(Collectors.toList());

                        // Save Order and OrderItems to the database
                        OrderDAO orderDAO = new OrderDAO();
                        orderDAO.addOrder(order, orderItems);

                        // Clear the cart
                        cartDAO.clearCart(user.getId());
                        response.getWriter().write("order_placed");
                    }
                    break;
                // Add more cases for other actions as needed
            }

            // Redirect back to the cart page
            response.sendRedirect("cart");
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login if user is not logged in
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CartDAO cartDAO = new CartDAO();

        if (user != null) {
            // Retrieve cart items for the user
            List<CartItem> cartItems = cartDAO.getCartItemsByUserId(user.getId());

            // Set cart items as request attribute
            request.setAttribute("cartItems", cartItems);

            // Forward to cart.jsp
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login if user is not logged in
        }
    }
}
