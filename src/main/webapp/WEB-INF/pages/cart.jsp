<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/cart.css">
<style>
body {
	font-family: 'Arial', sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f5f5f5;
}

.cart-container {
	max-width: 1200px;
	margin: 20px auto;
	padding: 20px;
	background-color: white;
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

.cart-container h1 {
	text-align: center;
	color: #333;
}

.cart-items-table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

.cart-items-table th, .cart-items-table td {
	padding: 15px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

.cart-items-table th {
	background-color: #4CAF50;
	color: white;
}

.cart-items-table tr:hover {
	background-color: #f1f1f1;
}

.product-image {
	max-width: 80px;
	max-height: 80px;
	object-fit: cover;
}

.quantity-input {
	width: 50px;
	text-align: center;
}

button {
	padding: 10px 20px;
	border: none;
	background-color: #4CAF50;
	color: white;
	border-radius: 5px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

.remove-item {
	background-color: #f44336;
}

.remove-item:hover {
	background-color: #e53935;
}

.total-row {
	font-weight: bold;
	text-align: right;
	background-color: #f1f1f1;
}

.success-message {
	background-color: #d4edda;
	color: #155724;
	padding: 10px;
	border-radius: 5px;
	text-align: center;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<%@ include file="includes/header.jsp"%>
	<!-- Spacer div to create space below the header -->
	<div style="height: 80px;"></div>

	<div class="cart-container">
		<h1>Your Shopping Cart</h1>

		<c:choose>
			<c:when test="${not empty cartItems}">
				<c:if test="${not empty sessionScope.checkoutMessage}">
					<div class="success-message">${sessionScope.checkoutMessage}
					</div>
					<!-- Remove the session attribute  -->
					<c:remove var="checkoutMessage" scope="session" />
				</c:if>
				<c:if test="${not empty sessionScope.removeMessage}">
					<div class="success-message">
						${sessionScope.removeMessage}
						<c:remove var="removeMessage" scope="session" />
					</div>
				</c:if>
				<c:if test="${not empty sessionScope.updateMessage}">
					<div class="success-message">
						${sessionScope.updateMessage}
						<c:remove var="updateMessage" scope="session" />
					</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/UpdateCart"
					method="post">
					<!-- Form for updating all quantities -->
					<table class="cart-items-table">
						<thead>
							<tr>
								<th>Product Image</th>
								<th>Product Name</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>Subtotal</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="total" value="0" />
							<!-- Initialize total -->

							<c:forEach var="item" items="${cartItems}">
								<c:set var="subtotal"
									value="${item.productPrice * item.quantity}" />
								<c:set var="total" value="${total + subtotal}" />

								<tr class="cart-item">
									<td><img src="data:image/jpeg;base64,${item.productImage}"
										alt="${item.productName}" class="product-image"></td>
									<td>${item.productName}</td>
									<td><input type="hidden" name="product_id"
										value="${item.productId}" /> <input type="number"
										name="quantity_${item.productId}" value="${item.quantity}"
										min="1" class="quantity-input" /></td>
									<td>${item.productPrice}</td>
									<td>${subtotal}</td>
									<!-- Display subtotal -->
									<td>
										<form action="<%=request.getContextPath()%>/RemoveItem" method="post">
											<input type="hidden" name="product_id"
												value="${item.productId}" />
											<button type="submit" class="remove-item">Remove</button>
											<!-- "Remove" button -->
										</form>
									</td>
								</tr>
							</c:forEach>

							<tr class="total-row">
								<td colspan="5" style="text-align: right;">Total:</td>
								<!-- Display the total -->
								<td>${total}</td>
							</tr>

							<!-- Row for "Update All" and "Proceed to Checkout" -->
							<tr>
								<td colspan="3" style="text-align: center;">
									<button type="submit">Update All</button> <!-- Update all quantities -->
								</td>
								<td colspan="3" style="text-align: center;">
									<form action="${pageContext.request.contextPath}/Checkout"
										method="post">
										<!-- Form for checkout -->
										<button type="submit">Proceed to Checkout</button>
										<!-- Proceed to checkout button -->
									</form>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
			</c:when>
			<c:otherwise>
				<c:if test="${not empty sessionScope.checkoutMessage}">
					<div class="success-message">${sessionScope.checkoutMessage}
					</div>
					<!-- Remove the session attribute  -->
					<c:remove var="checkoutMessage" scope="session" />
				</c:if>
				<p>Your cart is empty.</p>
				<!-- Message when the cart is empty -->
			</c:otherwise>
		</c:choose>
	</div>

	<%@ include file="includes/footer.jsp"%>
	<!-- Include the footer -->
</body>
</html>
