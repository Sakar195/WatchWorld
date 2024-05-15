<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Product Details</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
}

.go-back-button {
	background-color: #e74c3c;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 1em;
	transition: background-color 0.3s;
}

.go-back-button:hover {
	background-color: #c0392b;
}

.product-details {
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	padding: 20px;
	background: linear-gradient(to bottom, #ffffff, #f2f2f2);
}

.product-container {
	display: flex;
	flex-wrap: wrap;
	max-width: 1200px;
	background-color: white;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.1);
	border-radius: 10px;
	overflow: hidden;
	border: 1px solid #dcdcdc; /* Added border */
}

.product-image {
	flex: 1;
	max-width: 50%;
	min-width: 300px;
	padding: 20px;
	background-color: #f9f9f9;
	text-align: center;
	border-right: 1px solid #dcdcdc; /* Added border */
}

.product-image img {
	max-width: 100%;
	max-height: 400px;
	border-radius: 10px;
	border: 2px solid #e2e2e2; /* Added border to the image */
	transition: transform 0.3s;
}

.product-image img:hover {
	transform: scale(1.05);
}

.product-info {
	flex: 1;
	padding: 40px;
	color: #333;
}

.product-info h1 {
	font-size: 2em;
	margin-bottom: 20px;
}

.product-info h2 {
	font-size: 1.5em;
	color: #e67e22;
	margin-bottom: 10px;
}

.product-info h4 {
	font-size: 1.2em;
	margin-bottom: 20px;
}

.product-info p {
	font-size: 1em;
	line-height: 1.6;
	color: #555;
}

.buy-button {
	background-color: #3498db;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 1em;
	transition: background-color 0.3s;
}

div[style] {
	position: relative;
	z-index: 100; /* Ensures it's on top of other elements */
	display: block; /* Ensures it's visible */
}

.buy-button:hover {
	background-color: #2980b9;
}
</style>
</head>
<body>
	<%@ include file="includes/header.jsp"%>



	<c:set var="Product" value="${Product}"></c:set>

	<div class="product-details">
		<div class="product-container">
			<div class="product-image">
				<img src="data:image/*;base64,${Product.base64ImageData}"
					alt="${Product.name}" />
			</div>
			<div class="product-info">

				<button class="go-back-button"
					onclick="window.location.href='<%=request.getContextPath()%>/Product'">
					<i class="fa fa-arrow-left"></i>
				</button>

				<h1>${Product.name}</h1>
				<h2>Price: $${Product.price}</h2>
				<h4>Stock: ${Product.quantity}</h4>
				<p>Description: ${Product.description}</p>

				<!-- Button to buy the product -->
				<form action="${pageContext.request.contextPath}/AddCart"
					method="post">
					<input type="hidden" name="product_id" value="${Product.id}" /> <input
						type="hidden" name="referrer" value="productDetails" />
					<button class="buy-button"
						onclick="checkLoginAndNavigate(event, '<%=request.getContextPath()%>/AddCart', 'You need to log in to buy this product.')">Add
						to Cart</button>

					<!-- Check for the 'added_to_cart' parameter and display a success message if true -->
					<c:if test="${param.added_to_cart == 'true'}">
						<div
							style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin: 10px 0;">
							Item successfully added to cart!</div>
					</c:if>
					<!-- Check if there was an error indicating the item already exists in the cart -->
					<c:if
						test="${param.added_to_cart == 'false' && param.cart_error == 'item_already_exists'}">
						<div
							style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin: 10px 0;">
							Item already exists in the cart.</div>
					</c:if>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>
	<script>
	var isLoggedIn = <%=isLoggedIn ? "true" : "false"%>;
	 
	setIsLoggedIn("<%=isLoggedIn ? "true" : "false"%>");
	</script>
</body>
</html>
