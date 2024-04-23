<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="login-image"></div>
	<div class="login-box">
		<h2>Login</h2>
		<form action="<%=request.getContextPath()%>/Login" method="post"></form>
		<div class="row">
			<div class="col">
				<label for="username">Username:</label> <input type="text"
					id="username" name="username" placeholder="Username" required>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="password">Password:</label> <input type="text"
					id="password" name="password" placeholder="Password" required>
				<button type="submit" class="login-button">Login</button>
				<br>
				<br> <a href="<%=request.getContextPath()%>/Registration"
					style="color: green">SignUp</a>
			</div>
		</div>

	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>