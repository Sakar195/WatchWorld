<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Watch World</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/home.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

	<%@ include file="includes/header.jsp"%>

	<div class="image-slider">
		<img src="slider_image1.jpg" alt="Slider Image 1"> <img
			src="slider_image2.jpg" alt="Slider Image 2"> <img
			src="slider_image3.jpg" alt="Slider Image 3">
	</div>

	<section class="popular-items">
		<h2>Popular Items</h2>
		<div class="product">
			<img src="" alt="Product Image">
			<h3>Product Name</h3>
			<h4>Price</h4>
			<button>Add to Cart</button>
		</div>
		<div class="product">
			<img src="" alt="Product Image">
			<h3>Product Name</h3>
			<h4>Price</h4>
			<button>Add to Cart</button>
		</div>
		<div class="product">
			<img src="" alt="Product Image">
			<h3>Product Name</h3>
			<h4>Price</h4>
			<button>Add to Cart</button>
		</div>
		<div class="product">
			<img src="" alt="Product Image">
			<h3>Product Name</h3>
			<h4>Price</h4>
			<button>Add to Cart</button>
		</div>


		<!-- Popular Items Content Goes Here -->
	</section>

	<section class="feature-items">
		<h2>Feature Items</h2>
		<!-- Feature Items Content Goes Here -->
	</section>

	<%@ include file="includes/footer.jsp"%>

	<script src="home.js"></script>
</body>
</html>
