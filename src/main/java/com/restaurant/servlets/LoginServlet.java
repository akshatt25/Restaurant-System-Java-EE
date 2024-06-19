package com.restaurant.servlets;
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
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            User user = User.authenticateUser(email, password, conn);
            if (user != null) {
            	System.out.println("User retrieved from database: " + user.getUsername());
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                System.out.println("Session ID: " + session.getId());
                response.sendRedirect("dashboard");
            } else {
                request.setAttribute("error", "Invalid email or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=An error occurred. Please try again.");
        }
    }
}
