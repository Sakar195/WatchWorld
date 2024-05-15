<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product Page</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
}

.container {
	margin-top: 0px;
	padding: 10px;
	display: flex;
	flex-wrap: wrap;
	justify-content: flex-start;
}

.product-card {
	width: 250px;
	border: 1px solid #ddd;
	border-radius: 8px;
	padding: 15px;
	margin: 20px;
	box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
	transition: transform 0.2s, box-shadow 0.2s;
}

.product-card:hover {
	transform: scale(1.05);
	box-shadow: 4px 4px 15px rgba(0, 0, 0, 0.2);
}

.product-card img {
	width: 100%;
	border-radius: 8px;
}

.product-name {
	font-weight: bold;
	font-size: 16px;
	margin-top: 10px;
}

.price {
	font-weight: bold;
	font-size: 18px;
	color: #333;
	margin-top: 5px;
}

.button-group {
	display: flex;
	justify-content: space-between;
	margin-top: 10px;
}

.button-group button {
	padding: 8px 12px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.button-group button:hover {
	background-color: #45a049;
}

.filter-form {
	margin: 20px;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: white;
	box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.1);
}

.filter-form label {
	display: inline-block;
	margin-right: 10px;
	font-weight: bold;
}

.filter-form input {
	padding: 8px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

.filter-form button {
	padding: 8px 12px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.filter-form button:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
	<%@ include file="includes/header.jsp"%>
	<div style="height: 40px;"></div>
	<h1 style="margin-left: 20px;">Our Products</h1>

	<!-- Filter Form for Searching by Name and Price -->
	<div class="filter-form">
		<form action="FProducts" method="get">
			<label for="name">Name:</label> <input type="text" name="name"
				id="name" placeholder="Search by name" /> <label for="price">Price
				(up to):</label> <input type="number" name="price" id="price"
				placeholder="Search by price" step="0.01" />

			<button type="submit">Filter</button>
		</form>

	</div>
	<c:if test="${param.added_to_cart == 'true'}">
		<div class="filter-form"
			style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin: 10px 0;">
			Item successfully added to cart!</div>
	</c:if>
	<!-- Check if there was an error indicating the item already exists in the cart -->
	<c:if
		test="${param.added_to_cart == 'false' && param.cart_error == 'item_already_exists'}">
		<div class="filter-form"
			style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px; margin: 10px 0;">
			Item already exists in the cart.</div>
	</c:if>
	<!-- Display error message if a required parameter is missing -->
	<c:if test="${param.error == 'missing_product_id'}">
		<div style="color: red;">Product ID is missing. Please try
			again.</div>
	</c:if>


	<div class="container">
		<!-- Handle Empty Product List -->

		<c:choose>
			<c:when test="${empty productlist}">
				<p style="margin: 20px;">No products found.</p>
			</c:when>
			<c:otherwise>
				<div class="container">
					<c:forEach var="product" items="${productlist}">
						<div class="product-card">
							<img src="data:image/jpeg;base64,${product.base64ImageData}"
								alt="${product.name}" />
							<div class="product-name">
								<c:out value="${product.name}" />
							</div>
							<div class="price">
								$
								<c:out value="${product.price}" />
							</div>
							<!-- Button Group for Actions -->
							<div class="button-group">
								<form action="Details" method="get">
									<input type="hidden" name="product_id" value="${product.id}" />
									<button>View Details</button>
								</form>
								<form action="${pageContext.request.contextPath}/AddCart"
									method="post">
									<input type="hidden" name="product_id" value="${product.id}" />
									<input type="hidden" name="referrer" value="product" />
									<button
										onclick="checkLoginAndNavigate(event, '<%=request.getContextPath()%>/AddCart', 'You need to log in to buy this product.')">Add
										to Cart</button>
								</form>
							</div>
						</div>
					</c:forEach>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

	<%@ include file="includes/footer.jsp"%>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>
	<script>
	 
	var isLoggedIn = <%=isLoggedIn ? "true" : "false"%>;
	 
	setIsLoggedIn("<%=isLoggedIn ? "true" : "false"%>");
	</script>
</body>

</html>
