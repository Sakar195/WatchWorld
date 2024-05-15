<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	position: fixed;
	height: 10vh;
	width: 100vw;
	top: 0;
	background-color: #f0f0f0;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
	padding: 0 20px;
	z-index: 1000;
}

.logo {
	display: flex;
	align-items: center;
}

.logo img {
	height: 40px;
	width: auto;
}

.logo h3 {
	font-size: 18px;
	margin-left: 10px;
	font-weight: bold;
	color: #333;
}

.search-bar {
	display: flex;
	align-items: center;
	flex-grow: 1;
	justify-content: center;
}

.search-bar input {
	width: 60%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 16px;
}

.search-bar button {
	padding: 8px;
	border: none;
	background-color: #007bff;
	color: #fff;
	border-radius: 5px;
	cursor: pointer;
	transition: all 0.3s;
}

.search-bar button:hover {
	background-color: #005bb5;
}

nav {
	display: flex;
	align-items: center;
	padding: 10px;
}

nav ul {
	list-style-type: none;
	display: flex;
	align-items: center;
}

nav ul li {
	margin-right: 20px;
	transition: transform 0.3s;
}

nav ul li a {
	text-decoration: none;
	color: black;
	font-size: 16px;
	transition: color 0.3s;
}

nav a:hover {
	color: #007bff;
}

.login-button {
	padding: 8px 12px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.login-button:hover {
	background-color: #005bb5;
}

.cart {
	display: flex;
	align-items: center;
}

.cart a {
	text-decoration: none;
	color: #333;
	font-size: 16px;
	transition: all 0.3s;
}

.cart a:hover {
	color: #007bff;
}

.cart .fa {
	font-size: 20px;
}

@media ( max-width : 768px) {
	header {
		flex-direction: column;
		align-items: flex-start;
		padding: 10px;
	}
	.search-bar {
		width: 100%;
		justify-content: flex-start;
	}
	nav {
		width: 100%;
		justify-content: flex-start;
	}
	nav ul {
		flex-direction: column;
	}
	nav ul li {
		margin-right: 0;
		margin-bottom: 10px;
	}
	.cart {
		margin-top: 10px;
	}
}
</style>
</head>
<body>

	<header>
		<div class="logo">
			<img src="<%=request.getContextPath()%>/images/logo.jpg" alt="Logo">
			<h3>Watch World</h3>
		</div>
		<div class="search-bar">
			<input type="text" placeholder="Search here...">
			<button class="fa fa-search"></button>
		</div>
		<nav>
			<ul>
				<li><a href="<%=request.getContextPath()%>/Home">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/Product">Products</a></li>
				<li><a href="<%=request.getContextPath()%>/About">About Us</a></li>
				<%
				Integer loginValue = (session != null) ? (Integer) session.getAttribute("login_value") : null;
				boolean isLoggedIn = (loginValue != null && loginValue == 1);
				%>
				<%
				//login value 1 means user is logged in
				if (loginValue != null && loginValue == 1) {
				%>
				<li><form action="<%=request.getContextPath()%>/Logout"
						method="post">
						<button type="submit" onclick="confirmLogout()"
							class="login-button">Log Out</button>
					</form></li>
				<li><a href="<%=request.getContextPath()%>/Profile"><i
						class="fa fa-user fa-lg"></i> </a></li>
				<%
				}

				else {
				%>
				<li><form action="<%=request.getContextPath()%>/Login"
						method="post">
						<button type="submit" class="login-button">Log In</button>
					</form></li>
				<%
				}
				%>
			</ul>
		</nav>
		<div class="cart">
			<a href="#"
				onclick="checkLoginAndNavigate(event, '<%=request.getContextPath()%>/Cart','You need to log in to view your cart.')"><i
				class="fa fa-shopping-bag"></i></a>
		</div>
	</header>
	<script src="<%=request.getContextPath()%>/js/script.js"></script>
	<script>
	 
	var isLoggedIn = <%=isLoggedIn ? "true" : "false"%>;
	 
	setIsLoggedIn("<%=isLoggedIn ? "true" : "false"%>");
	</script>
</body>
</html>






























