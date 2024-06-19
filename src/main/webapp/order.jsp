<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.restaurant.models.Order" %>
<%@ page import="com.restaurant.models.OrderItem" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" type="text/css" href="css/order.css">
</head>
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
<body>

    <div class="container">
        <h1>Orders</h1>

        <!-- Current Order -->
        <!-- Current Order -->
<div class="current-order">
    <h2>Current Order</h2>
    <div class="order">
        <% Order currentOrder = (Order) request.getAttribute("currentOrder");
        if (currentOrder != null) { %>
            <h3>Order ID: <%= currentOrder.getOrderId() %></h3>
            <!-- Display other current order details -->
            <h4>Date & Time: <%= currentOrder.getTimestamp() %></h4>
            <h4>Contact Number: <%= currentOrder.getCustomerContact() %></h4>
            <h4>Amount: &#8377;<%= currentOrder.getAmount() %></h4>
            <h4>Instructions: <%= currentOrder.getInstructions() %></h4>
        <% } else { %>
            <p>No current order found.</p>
        <% } %>
    </div>
</div>


        <!-- Your Bill -->
       <!-- Your Bill -->
<div class="your-bill">
    <h2>Your Bill</h2>
    <div class="bill-items">
        <table>
            <thead>
                <tr>
                    <th>Item</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <% List<OrderItem> billItems = currentOrder.getOrderItems();
                for (OrderItem item : billItems) { %>
                    <tr>
                        <td><%= item.getItemName() %></td>
                        <td><%= item.getQuantity() %></td>
                        <td>&#8377;<%= item.getPrice() %></td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <div class="tax-service">
            <p>Tax & Services (18%): &#8377;<!-- Calculate and display tax and services here --></p>
        </div>
        <div class="total">
            <h3>Total: &#8377;<%= currentOrder.getAmount() %></h3>
        </div>
    </div>
</div>

    </div>
</body>
</html>
