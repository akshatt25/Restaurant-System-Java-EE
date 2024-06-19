package com.restaurant.servlets;

import com.restaurant.dao.CartDAO;
import com.restaurant.dao.MenuDAO;
import com.restaurant.models.MenuItem;
import com.restaurant.models.User;
import com.restaurant.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private MenuDAO menuDAO;
    private CartDAO cartDAO;

    @Override
    public void init() {
//        Connection conn = DBConnection.getConnection();
        menuDAO = new MenuDAO();
        cartDAO = new CartDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String category = request.getParameter("category");

        if (user != null) {
            List<MenuItem> menuItems;
            if (category != null && !category.isEmpty()) {
                menuItems = menuDAO.getMenuItemsByCategory(category);
            } else {
                menuItems = menuDAO.getAllMenuItems();
            }

            request.setAttribute("menuItems", menuItems);
            request.setAttribute("selectedCategory", category);
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            String action = request.getParameter("action");
            if ("addToCart".equals(action)) {
                int menuItemId = Integer.parseInt(request.getParameter("menuItemId"));
                MenuItem menuItem = menuDAO.getMenuItemById(menuItemId);
                cartDAO.addToCart(user.getId(), menuItemId, 1, menuItem.getName(), menuItem.getPrice(), menuItem.getImage());
                response.getWriter().write("added");
            }
        } else {
            response.sendRedirect("login.jsp");
        }
    }
}
