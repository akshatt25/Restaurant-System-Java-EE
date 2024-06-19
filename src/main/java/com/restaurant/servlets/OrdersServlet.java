package com.restaurant.servlets;

import com.restaurant.dao.OrderDAO;
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

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("username");
        User user = (User) session.getAttribute("user");
        if (user != null) {
            OrderDAO orderDAO = new OrderDAO();
            List<Order> orders = orderDAO.getOrdersByUserId(user.getId());

            // Separate the latest order from the rest
            Order currentOrder = orders.isEmpty() ? null : orders.remove(0);

            // Fetch order items for the latest order
            List<OrderItem> currentOrderItems = null;
            if (currentOrder != null) {
                currentOrderItems = currentOrder.getOrderItems();
            }

            request.setAttribute("currentOrder", currentOrder);
            request.setAttribute("previousOrders", orders);
            request.setAttribute("currentOrderItems", currentOrderItems);
            System.out.println(currentOrder.getItems());
            System.out.println(currentOrderItems.get(0).getItemName());
            request.getRequestDispatcher("order.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
