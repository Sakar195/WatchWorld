<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.products-container {
	max-width: 1300px;
	margin: auto;
	padding: 50px 10px;
}
</style>
</head>
<body>
	<%@ include file="includes/header.jsp"%>
	<h1 style="margin-left: 20px;">Our Products</h1>

	<div class="container">



		<div class="products-container">
			<div class="row">
				<div class="col-2">
					<img src="../img/p1.jpg">
				</div>
				<div class="col-2" style="padding-left: 20px;">
					<h1>Ficus</h1>
					<br>
					<h2>
						<del>$10.00</del>
						$8.00
					</h2>
					
						<a href="../html/products.html">Buy now</a>
					</button>
					<br> <br>
					<p>
						
				</div>
			</div>
		</div>
	</div>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>