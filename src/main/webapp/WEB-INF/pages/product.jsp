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
.container {
	margin-top: 100px;
	padding: 20px;
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
	transition: transform 0.2s;
}

.product-card:hover {
	transform: scale(1.05);
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
	display: flex; /* Ensure buttons are displayed in a row */
	justify-content: space-between; /* Space out buttons */
	margin-top: 10px; /* Add spacing between price and buttons */
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
</style>
</head>
<body>
	<%@ include file="includes/header.jsp"%>
		 <h1 style="margin-left: 20px;">Our Products</h1>

	<div class="container">
	

		<!-- Handle Empty Product List -->
		<c:choose>
			<c:when test="${productlist == null}">
				<p>Product list is unavailable.</p>
			</c:when>
			<c:when test="${productlist.isEmpty()}">
				<p>No products found.</p>
			</c:when>
			<c:otherwise>
				<!-- Display Product Cards -->
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
							<!-- View Details Button -->
							<form action="Details" method="get">
								<input type="hidden" name="id" value="${product.id}" />
								<button>View Details</button>
							</form>

							<!-- Add to Cart Button -->
							<form action="AddToCart" method="post">
								<input type="hidden" name="id" value="${product.id}" />
								<button>Add to Cart</button>
							</form>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>
