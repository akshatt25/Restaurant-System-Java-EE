package com.restaurant.servlets;
import com.restaurant.models.User;
import com.restaurant.util.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        try (Connection conn = DBConnection.getConnection()) {
            if (User.emailExists(email, conn)) {
                response.sendRedirect("signup.jsp?error=Email already exists.");
                return;
            }

            User user = new User(username, password, email);
            if (user.saveUser(conn)) {
                response.sendRedirect("signup.jsp?message=User created successfully.");
            } else {
                response.sendRedirect("signup.jsp?error=An error occurred. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("signup.jsp?error=An error occurred. Please try again.");
        }
    }
}
