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
	margin-left: 250px;
	display: flex;
	flex-wrap: wrap;
	align-items: center;
}

.product-card {
	width: 200px;
	height: 250px;
	border: 1px solid #ccc;
	border-radius: 8px;
	padding: 10px;
	margin-left: 30px;
	margin-bottom: 30px;
	display: inline-block;
	vertical-align: top;
}

.product-card img {
	width: 100%;
	border-radius: 8px;
}

.product-card .price {
	font-weight: bold;
	color: #333;
	margin-top: 5px;
}

.product-card .description {
	margin-top: 5px;
	color: #666;
}
</style>
</head>
<body>
<%@include file="includes/header.jsp" %>
	<div class="container">
		<h1>Our Products</h1>
		<!-- Debug output to check if productdb is null or empty -->
		<c:if test="${productlist == null}">
			<p>Product list is null</p>
		</c:if>

		<c:if test="${productdb != null && productdb.isEmpty()}">
			<p>Product list is empty</p>
		</c:if>
		<div class="product-list">
			<!-- Product Cards -->
			<c:forEach var="product" items="${productlist}">
				<div class="product-card">
					<img src="data:image/jpeg;base64,${product.base64ImageData}"
						height="200px" width="200px" alt="Product 1">
					<c:out value="${product.name }"></c:out>
					<div class="price">${product.price}</div>
					<!-- <div class="description">${product.description}</div> -->
				</div>
			</c:forEach>

		</div>
	</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>