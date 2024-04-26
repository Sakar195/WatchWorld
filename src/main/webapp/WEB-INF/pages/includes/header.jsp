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

<header>
	<div class="logo">
		<img src="images/logo.png" alt="Logo">
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
			<li><a href="#">About Us</a></li>
			<%
			Integer login_value = (Integer) request.getAttribute("login_value");
			if (login_value != null && login_value == 1) {
			%>
			<li><form action="<%=request.getContextPath()%>/Logout" method="post"><button type="submit" onclick="confirmLogout()" >Log Out</button></form></li>
			<%
			}

			else {
			%>
			<li><form action="<%=request.getContextPath()%>/Login" method="post"><button type="submit">Log In</button></form></li>
			<%
			}
			%>
		</ul>
	</nav>
	<div class="cart">
		<a href="<%=request.getContextPath()%>/Cart"><i
			class="fa fa-shopping-bag"></i></a>
	</div>
</header>
<script>
function confirmLogout() {
    alert("You have logged out."); // This alert pops up when you click the logout button
}
</script>
