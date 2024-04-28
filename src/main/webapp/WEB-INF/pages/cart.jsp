<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Shopping Cart</title>
 <link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cart.css">
</head>
<body>
  <%@ include file="includes/header.jsp" %> <!-- Include your header -->

  <div class="cart-container">
    <h1>Your Shopping Cart</h1>

    <div class="cart-items">
      <!-- Example of a cart item -->
      <div class="ca.rt-item">
        <img src="images/product1.jpg" alt="Product Image" class="product-image">
        <div class="product-info">
          <h2 class="product-name">Product Name</h2>
          <p class="product-description">Short product description</p>
          <p class="product-price">$29.99</p>
          <div class="quantity">
            <label for="quantity">Qty:</label>
            <input type="number" id="quantity" name="quantity" value="1" min="1">
          </div>
          <button class="remove-item">Remove</button>
        </div>
      </div>
      <!-- Additional cart items would go here -->
    </div>

    <div class="cart-summary">
      <h2>Summary</h2>
      <p>Subtotal: $29.99</p>
      <p>Tax: $2.40</p>
      <p>Total: $32.39</p>
    </div>

    <div class="cart-actions">
      <button class="checkout">Proceed to Checkout</button>
      <a href="products.jsp" class="continue-shopping">Continue Shopping</a>
    </div>
  </div>

  <%@ include file="includes/footer.jsp" %> <!-- Include your footer -->
</body>
</html>