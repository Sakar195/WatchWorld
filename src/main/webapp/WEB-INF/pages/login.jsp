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
	<%@ include file="includes/header.jsp"%>
	<div class="container">
		<!-- Place image on the left -->
		<div class="login-image">
			<img src="${pageContext.request.contextPath}/images/Login.png"
				alt="Login Image" >
			<!-- Ensure the path is correct -->
		</div>

		<!-- Place login box on the right -->
		<div class="login-box">
			<h2>Login</h2>
			<form action="${pageContext.request.contextPath}/Login" method="post">
				<div class="row">
					<div class="col">
						<label for="username">Username:</label> <input type="text"
							id="username" name="username" value="${username}"
							placeholder="Enter your Username Here" required>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<label for="password">Password:</label> <input type="password"
							id="password" name="password"
							placeholder="Enter your Password Here" required>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<button type="submit" name="login-button" class="login-button">Login</button>
					</div>
				</div>
				<div class="row">
					<div class="col">
						<a href="<%=request.getContextPath()%>/Registration"
							style="color: green">SignUp</a>
						<%
						if (request.getAttribute("error") != null) {
						%>
						<p style="color: red"><%=request.getAttribute("error")%></p>
						<%
						}
						%>
					</div>
				</div>
			</form>
		</div>
	</div>
	<%@ include file="includes/footer.jsp"%>
</body>
</html>
