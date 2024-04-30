<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.product-details {
	max-width: 100vw;
	max-height: 100vh;
	margin-top: 100px;
	padding: 50px 10px;
}

.product-image {
	width: 300px;
	height: auto;
}

.product-info {
	padding-left: 20px;
}
</style>
</head>
<body>
	<%@ include file="includes/header.jsp"%>

<c:set var="Product" value="${Product}"></c:set>
	<div class="product-details">
		<div class="row">
			<div class="col-2">
				<img class="product-image"
					src="data:image/*;base64,${Product.base64ImageData}"
					alt="${Product.name}" />
			</div>
			<div class="col-2 product-info">
				<h1>${Product.name}</h1>
				<br>
				<h2>Price: $${Product.price}</h2>
				<p>${Product.description}</p>

				<!-- Button to buy the product -->
				<form action="AddToCart" method="post">
					<input type="hidden" name="id" value="${Product.id}" />
					<button>Buy Now</button>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>
</body>
</html>