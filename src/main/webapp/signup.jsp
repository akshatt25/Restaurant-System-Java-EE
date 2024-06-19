<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
    <div class="container">
        <h1>Sign Up</h1>
        <form action="signup" method="post">
            <input type="text" name="username" placeholder="Name" required><br>
           
            <input type="email" name="email" placeholder="Email" required><br>
             <input type="password" name="password" placeholder="Password" required><br>
            <input type="submit" value="Sign Up">
        </form>
        <% if (request.getParameter("message") != null) { %>
            <div class="message">
                <p><%= request.getParameter("message") %></p>
                <form action="login.jsp" method="get">
                    <button type="submit">Please Login</button>
                </form>
            </div>
        <% } else if (request.getParameter("error") != null) { %>
            <div class="message error">
                <p><%= request.getParameter("error") %></p>
            </div>
        <% } %>
    </div>
</body>
</html>
