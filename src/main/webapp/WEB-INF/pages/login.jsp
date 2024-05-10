<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/login.css">
<style>
body {
  font-family: sans-serif;
  margin: 0;
  padding: 0;
  background-image: url("<%= request.getContextPath() %>/images/Login.png"); 
  background-size: cover;
  background-repeat: no-repeat;
  width: 100%;
  height: 100vh;
  display: flex; /* Flex container */
  justify-content: center; /* Center horizontally */
  align-items: center; /* Center vertically */
}
</style>
</head>
<body>
	<%@ include file="includes/header.jsp"%>
	<div class="container">
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
	
</body>
</html>
