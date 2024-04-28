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

	<div class="slider-container">
		<div class="slider">
			<img src="slider1.jpg" alt="Smartwatches Slider"> <img
				src="slider2.jpg" alt="Smartwatches Slider"> <img
				src="slider3.jpg" alt="Smartwatches Slider">
		</div>
	</div>

	<div class="container">
		<h2 class="descrip">Smartwatch Store</h2>
		<p style="margin-left: 50px;">
			Welcome to Watch World, your premier destination for the latest
			smartwatches. Browse our collection of stylish <br> and
			innovative timepieces designed to keep you connected and on trend.
			From fitness tracking to messaging alerts, <br> our smartwatches
			offer a range of features to enhance your lifestyle. Shop now and
			discover the perfect companion <br> for your wrist.
		</p>

		<h2>Popular Products</h2>
		<div class="products">
			<div class="product">
				<img src="product1.jpg" alt="Product 1" />
				<h3>Smartwatch 1</h3>
				<p>$199.99</p>
			</div>
			<div class="product">
				<img src="product2.jpg" alt="Product 2" />
				<h3>Smartwatch 2</h3>
				<p>$249.99</p>
			</div>
			<div class="product">
				<img src="product3.jpg" alt="Product 3" />
				<h3>Smartwatch 3</h3>
				<p>$179.99</p>
			</div>
		</div>

		<h2>Featured Products</h2>
		<div class="products">
			<div class="product">
				<img src="product4.jpg" alt="Product 4" />
				<h3>Smartwatch 4</h3>
				<p>$299.99</p>
			</div>
			<div class="product">
				<img src="product5.jpg" alt="Product 5" />
				<h3>Smartwatch 5</h3>
				<p>$349.99</p>
			</div>
			<div class="product">
				<img src="product6.jpg" alt="Product 6" />
				<h3>Smartwatch 6</h3>
				<p>$199.99</p>
			</div>
		</div>
	</div>

	<%@ include file="includes/footer.jsp"%>

	<script src="home.js"></script>
</body>
</html>
