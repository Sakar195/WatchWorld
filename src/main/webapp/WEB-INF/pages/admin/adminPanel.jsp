<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin Dashboard - Watch World</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.container {
	display: flex;
	justify-content: space-around;
	padding: 20px;
}

.sidebar {
	background-color: #333;
	color: #fff;
	width: 200px;
	padding: 20px;
	border-radius: 8px;
}

.sidebar h2 {
	color: #fff;
	margin-bottom: 20px;
}

.menu-item {
	list-style: none;
	margin: 0;
	padding: 0;
}

.menu-item a {
	display: block;
	padding: 10px;
	color: #fff;
	text-decoration: none;
	transition: background-color 0.3s;
}

.menu-item a:hover {
	background-color: #555;
	border-radius: 4px;
}

.main-content {
	flex-grow: 1;
	padding: 20px;
}

.button {
	background-color: #007bff;
	border: none;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 8px;
}

.button:hover {
	background-color: #22234b;
}

.card {
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	padding: 20px;
	margin-bottom: 20px;
}
</style>
</head>
<body>
<% //include file="../includes/header.jsp"%>
	<div class="container">
		<div class="sidebar">
			<h2>Menu</h2>
			<ul class="menu-item">
				<li><a href="#">Dashboard</a></li>
				<li><a href="<%=request.getContextPath()%>/VProduct">View Products</a></li>
				<li><a href="<%=request.getContextPath()%>/AddProduct">Add New Product</a></li>
				<li><a href="<%=request.getContextPath()%>/User">Users</a></li>
				<li><a href="<%=request.getContextPath()%>/Orders">Orders</a></li>
				<li><a href="<%=request.getContextPath()%>/Profile">Go Back</a></li>
				
			</ul>
			<form action="<%=request.getContextPath()%>/Logout" method="post">
			<button type="submit"   class="button" onclick="confirmLogout()">Logout</button>
			</form>
		</div>
		<div class="main-content">
			<div class="card">
				<h2>View Products</h2>
				<p>View all the products available in the store.</p>
				<a href="<%=request.getContextPath()%>/VProduct" class="button">View Products</a>
			</div>
			<div class="card">
				<h2>Add New Product</h2>
				<p>Add a new smartwatch product to the store.</p>
				<a href="<%=request.getContextPath()%>/AddProduct" class="button">Add Product</a>
			</div>
			<div class="card">
				<h2>Users</h2>
				<p>View all the users. </p>
				<a href="#" class="button">View Statistics</a>
			</div>
			<div class="card">
				<h2>Total Products</h2>
				<p>Display the total number of smartwatch products in the store.</p>
				<p>
					<strong>Total Products: </strong>100
				</p>
			</div>
		</div>
	</div>
</body>
<script>
function confirmLogout() {
    alert("You have logged out."); // This alert pops up when you click the logout button
}
</script>
</html>

