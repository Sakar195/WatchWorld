<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Update Product</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/addProduct.css">
</head>
<body>
		
<c:set var="Product" value="${Product}"></c:set>
	<form action="<%=request.getContextPath()%>/Update" method="post" enctype="multipart/form-data">
		<div class="container">
			<h2>Update Product</h2>
			<%
			if (request.getAttribute("message") != null) {
			%>
			<p style="color: green"><%=request.getAttribute("message")%></p>
			<%
			}
			%>
			<div class="Details">
				<label for="productName">Product Name:</label> <input type="text"
					class="productName" name="productName" value="${Product.name}" required>
			</div>
			<div class="Details">
				<label for="productDescription">Description:</label>
				<textarea name="productDescription" required>${Product.description}</textarea>

			</div>
			<div class="Details">
				<label for="productImage">Product Image:</label>
    			<!-- Display current image if available -->
    			<img src="data:image/*;base64,${Product.base64ImageData}" alt="Current Image" height="60px" width="60px">
    			<input type="file" name="productImage" accept="image/*">
			</div>
			<div class="Details">
				<label for="productPrice">Price:</label> <input type="number"
					class="productPrice" name="productPrice" value="${Product.price}"  required>
			</div>
			<div class="Details">
				<label for="Quantity">Quantity:</label> <input type="number"
					id="Quantity" name="Quantity" min="0" max="1000"  value="${Product.quantity}" required>
			</div>
			<%
			if (request.getAttribute("error") != null) {
			%>
			<p style="color: red"><%=request.getAttribute("error")%></p>
			<%
			}
			%>
			<button type ="submit" >Update Product</button> 
			<a href="<%=request.getContextPath()%>/VProduct" style="color: green">Go Back</a>
		</div>
	</form>
</body>
</html>