<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.restaurant.models.CartItem" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Cart</title>
    <link rel="stylesheet" type="text/css" href="css/cartPage.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
        	$('.remove-from-cart-btn').click(function() {
                var button = $(this);
                var cartItemId = button.data('id');
                
                // Show the loader
                $('.loader').show();
                
                // Send the remove action to the servlet
                $.post('cart', { action: 'remove', cartItemId: cartItemId }, function(response) {
                	 $('.loader').hide();
                     location.reload();
                	if (response === 'removed') {
                        // Hide the loader
                       
                    }
                });
            });

            $('.increase-btn').click(function() {
                var button = $(this);
                var cartItemId = button.data('id');
                $.post('cart', { action: 'increaseQuantity', cartItemId: cartItemId }, function(response) {
                	var quantityElement = button.siblings('.quantity');
                    var newQuantity = parseInt(quantityElement.text()) + 1;
                    quantityElement.text(newQuantity);
                    location.reload();
                	if (response === 'increased') {
                        
                    }
                });
            });

            // Decrease quantity button
            $('.decrease-btn').click(function() {
                var button = $(this);
                var cartItemId = button.data('id');
                $.post('cart', { action: 'decreaseQuantity', cartItemId: cartItemId }, function(response) {
                	var quantityElement = button.siblings('.quantity');
                    var newQuantity = parseInt(quantityElement.text()) - 1;
                    if (newQuantity >= 1) {
                        quantityElement.text(newQuantity);
                        location.reload();
                    }
                	if (response === 'decreased') {
                        
                    }
                });
            });
            $('.checkout-btn').click(function() {
                $('.dialog-overlay').show();
            });

            $('.confirm-btn').click(function() {
                var contactNumber = $('.contact').val();
                var instructions = $('.instructions').val();
                if (contactNumber.trim() === "") {
                    alert("Please enter a contact number.");
                    return;
                }
                $.post('cart', { action: 'checkout', contactNumber: contactNumber, instructions: instructions }, function(response) {
                    window.location.href = "orders";
                });
            });

            $('.cancel-btn').click(function() {
                $('.dialog-overlay').hide();
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
    <h1>My Cart</h1>
    <div class="cart-items">
    <% List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
    if (cartItems != null && !cartItems.isEmpty()) { %>
        <div class="cart-item-container">
            <% double totalPrice = 0;
            for (CartItem cartItem : cartItems) {
                double itemTotal = cartItem.getQuantity() * cartItem.getPrice();
                totalPrice += itemTotal;
            %>
                <div class="cart-item">
                    <div class="cart-item-img">
                        <img src="<%= cartItem.getImage() %>" alt="<%= cartItem.getName() %>">
                    </div>
                    <div class="cart-item-details">
                        <div class="item-info">
                            <h3><%= cartItem.getName() %></h3>
                            <p>Price: <%= cartItem.getPrice() %></p>
                        </div>
                        <div class="quantity-counter">
                            <button class="decrease-btn" data-id="<%= cartItem.getId() %>">-</button>
                            <span class="quantity"><%= cartItem.getQuantity() %></span>
                            <button class="increase-btn" data-id="<%= cartItem.getId() %>">+</button>
                        </div>
                    </div>
                    <div class="item-total">
                        <p>Total: <%= itemTotal %></p>
                    </div>
                     <div class="space">
                       
                    </div>
                    <div class="actions">
                        <button class="remove-from-cart-btn" data-id="<%= cartItem.getId() %>">Remove</button>
                    </div>
                </div>
            <% } %>
        </div>
        <div class="total-price">
            <h2>Total: $<%= totalPrice %></h2>
        </div>
        <button  class="checkout-btn">Checkout</button>
    <% } else { %>
        <p>Your cart is empty.</p>
    <% } %>
</div>
    
</div>
<div class="dialog-overlay"  style="display: none;">
    <div class="dialog-box">
        <h2>Confirm Your Order</h2>
        <label for="contact">Contact Number:</label>
        <input type="text" class="contact" name="contact">

        <label for="instructions">Special Instructions:</label>
        <textarea class="instructions" name="instructions" rows="4"></textarea>

        <div class="dialog-buttons">
            <button class="confirm-btn" >Confirm Order</button>
            <button class="cancel-btn">Cancel</button>
        </div>
    </div>
</div>
<div class="loader" style="display: none;">
    <div class="spinner-border" role="status">
        <span class="sr-only"></span>
    </div>
</div>

</body>
</html>
