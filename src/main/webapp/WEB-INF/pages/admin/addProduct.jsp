<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Add New Product</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/addProduct.css">
</head>
<body>
	<form action="<%=request.getContextPath()%>/addproduct" method="post" enctype="multipart/form-data">
		<div class="container">
			<h2>Add New Product</h2>
			<div class="Details">
				<label for="productName">Product Name:</label> <input type="text"
					class="productName" name="productName" required>
			</div>
			<div class="Details">
				<label for="productDescription">Description:</label>
				<textarea class="productDescription" name="productDescription"
					required></textarea>
			</div>
			<div class="Details">
				<label for="productImage">Product Image:</label> <input type="file"
					class="productImage" name="productImage"
					accept="image/png, image/jpeg, image/jpg" required>
			</div>
			<div class="Details">
				<label for="productPrice">Price:</label> <input type="number"
					class="productPrice" name="productPrice" required>
			</div>
			<div class="Details">
				<label for="discount">Quantity:</label> <input type="number"
					id="Quantity" name="Quantity" min="0" max="50" required>
			</div>
			<button type ="submit" >Add Product</button> 
			<button href="<%=request.getContextPath()%>/admin" style="color: red ">Go Back</button>
		</div>
	</form>
</body>
</html>