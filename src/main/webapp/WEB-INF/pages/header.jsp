
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
}

.logo {
	padding-left: 50px;
	display: inline-block;
}

.logo img {
	height: 50px;
}

.logo h3 {
	font-size: 16px;
	margin-left: 10px;
}

.search-bar {
	display: inline-block;
	width: 30vw;
	left: 30%;
	margin: 10px 10px 10px 100px;
	border: none;
	border-radius: 10px;
	font-size: 16px;
}

.search-bar input {

	width: 20%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 16px;
}

.search-bar button {
	margin-left: 20px;
	padding: 8px 20px;
	border: none;
	background-color: #007bff;
	color: #fff;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.search-bar button:hover {
	color: red;
}

.login {
	text-decoration: none;
	color: #fff;
	font-size: 16px;
	padding-right: 100px;
}

.cart {
	margin-left: 10px;
	padding-right: 50px;
	text-decoration: none;
	color: #333;
	font-size: 16px;
}

nav {
	flex: 1;
	text-align: right;
	padding: 10px;
}

nav ul {
	list-style-type: none;
	display: inline-block;
}

nav ul li {
	display: inline-block;
	margin-right: 20px;
	transition: transform 0.5s;
}

nav ul li a {
	text-decoration: none;
	color: black;
	font-size: 16px;
}

nav a:hover {
	color: red;
}
</style>
<header>
	<div class="logo">
		<img src="images/logo.png ">
		<h3>Watch World</h3>
	</div>
	<div class="search-bar">
		<input type="text" placeholder="Search here...">
		<button class="fas fa-search"></button>
	</div>
	<nav>
		<ul>
			<li><a href="<%=request.getContextPath()%>/Home">Home</a></li>
			<li><a href="<%=request.getContextPath()%>/Product">Products</a></li>
			<li><a href="#">About Us</a></li>
			<li><a href="<%=request.getContextPath()%>/Login">Log In</a></li>
		</ul>
	</nav>

	<div class="cart">
		<a href="#">
			<button class=" fa fa-shopping-bag"></button>
		</a>
	</div>
</header>