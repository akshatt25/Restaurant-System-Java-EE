<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel</title>
    <link rel="stylesheet" type="text/css" href="css/admin.css">
</head>
<body>
<nav class="navbar">
        <div class="navbar-left">
            <h2>Welcome Admin!</h2>
        </div>
        <div class="navbar-right">
            <a href="#">View Menu</a>
        </div>
         <div class="navbar-right">
            <a href="#">View Orders</a>
        </div>
    </nav>

<div class="container">
    
    <div class="add-to-cart">
        <h2>Add to Cart</h2>
        <form action="cart" method="post">
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>

            <label for="category">Category:</label>
            <select id="category" name="category">
                <option value="Appetizers">Appetizers</option>
                <option value="Mains">Mains</option>
                <option value="Desserts">Desserts</option>
                <option value="Beverages">Beverages</option>
                <option value="Italian">Italian</option>
                <option value="Chinese">Chinese</option>
            </select>

            <label for="price">Price:</label>
            <input type="number" id="price" name="price" min="0" step="0.01" required>

            <label for="image">Image URL:</label>
            <input type="url" id="image" name="image" required>

            <label for="description">Description:</label>
            <textarea id="description" name="description" rows="4" required></textarea>

            <input type="submit" value="Add to Cart">
        </form>
    </div>
</div>
</body>
</html>
