<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.restaurant.models.MenuItem" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/dashboard.css">
    <link rel="stylesheet" type="text/css" href="css/menu.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.add-to-cart-btn').click(function() {
                var button = $(this);
                var menuItemId = button.data('id');

                $.post('dashboard', { action: 'addToCart', menuItemId: menuItemId }, function(response) {
                    if (response === 'added') {
                        button.text('Added').prop('disabled', true).css('background-color', '#888');
                    }
                });
            });
        });
    </script>
</head>
<body>
<header>
    <nav>
        <div class="welcome">
            <span>Welcome, ${user.username}</span>
        </div>
        <div class="icons">
            <div class="icon">
                <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/2e/Microsoft_Account_Logo.svg/800px-Microsoft_Account_Logo.svg.png" alt="Account Icon" class="icon-image">
                <span>Account</span>
            </div>
            
            <a href="cart"><div class="icon">
            
                <img src="https://cdn.pixabay.com/photo/2015/12/23/01/14/edit-1105049_1280.png" alt="Orders Icon" class="icon-image">
             
                <span>Cart</span>
           
            </div>
              </a>
              <a href ="orders">
            <div class="icon">
                <img src="https://cdn-icons-png.flaticon.com/512/1404/1404970.png" alt="Orders Icon" class="icon-image">
                <span>My Orders</span>
            </div>
            </a>
        </div>
    </nav>
</header>
<div class="container">
    <h1>Menu</h1>
    <!-- Filter chips -->
    <div class="filter-chips">
        <a href="dashboard">All</a>
        <a href="dashboard?category=appetizers" class="${selectedCategory == 'appetizers' ? 'active' : ''}">Appetizers</a>
        <a href="dashboard?category=mains" class="${selectedCategory == 'mains' ? 'active' : ''}">Mains</a>
        <a href="dashboard?category=desserts" class="${selectedCategory == 'desserts' ? 'active' : ''}">Desserts</a>
        <a href="dashboard?category=beverages" class="${selectedCategory == 'beverages' ? 'active' : ''}">Beverages</a>
        <a href="dashboard?category=chinese" class="${selectedCategory == 'chinese' ? 'active' : ''}">Chinese</a>
        <a href="dashboard?category=italian" class="${selectedCategory == 'italian' ? 'active' : ''}">Italian</a>
    </div>
    <!-- Menu items -->
    <div class="menu-grid">
        <% List<MenuItem> menuItems = (List<MenuItem>) request.getAttribute("menuItems");
        if (menuItems != null && !menuItems.isEmpty()) {
            for (MenuItem menuItem : menuItems) { %>
                <div class="menu-item">
                    <img src="<%= menuItem.getImage() %>" alt="<%= menuItem.getName() %>">
                    <div class="menu-item-details">
                        <h2><%= menuItem.getName() %></h2>
                        <p><%= menuItem.getDescription() %></p>
                        <p>Price: <%= menuItem.getPrice() %></p>
                    </div>
                    <div class="menu-item-button">
                        <button class="add-to-cart-btn" data-id="<%= menuItem.getId() %>">Add</button>
                    </div>
                </div>
            <% }
        } else { %>
            <p>No menu items found.</p>
        <% } %>
    </div>
</div>
</body>
</html>
