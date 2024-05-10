
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/register.css" />
<style>
#role {
	width: 150px;
}
body {
    font-family: sans-serif;
    margin: 0;
    padding: 20px;
    margin-top: 40px;
    background-image: url('<%= request.getContextPath() %>/images/Login.png');
    background-size: cover;
    background-repeat: no-repeat;
    justify-content: center;
  	height: 90vh;
    display: flex;
    flex-direction: column; 
}

</style>
</head>
<body>
	<%@ include file="includes/header.jsp"%>
	<div style="height: 80px;"></div>
	<div class="container">
		<h1>Register</h1>
		<%
		if (request.getAttribute("error1") != null) {
		%>
		<p style="color: red"><%=request.getAttribute("error1")%></p>
		<%
		}
		%>

		<form action="<%=request.getContextPath()%>/Registration" id="myForm"
			method="post">
			<div class="row">
				<div class="col">
					<label for="firstName">First Name:</label> <input type="text"
						id="firstName" value="${firstName}" name="firstName" required>
				</div>
				<div class="col">
					<label for="lastName">Last Name:</label> <input type="text"
						id="lastName" value="${lastName}" name="lastName" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="email">Email:</label> <input type="email" id="email"
						value="${email}" name="email" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="email">Address:</label> <input type="text" id="address"
						value="${address}" name="address" required>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="gender">Gender:</label> <select id="gender"
						name="gender" required>
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</div>
				<div class="col">
					<label for="username">Username:</label> <input type="text"
						id="userName" value="${userName} " name="userName" required>
				</div>
			</div>

			<div class="row">
				<div class="col">
					<label for="phoneNumber">Phone Number:</label> <input type="tel"
						id="phoneNumber" value="${phoneNumber }" name="phoneNumber"
						required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
				<div class="col">
					<label for="retypePassword">Retype Password:</label> <input
						type="password" id="retypePassword" name="retypePassword" required>
				</div>
			</div>

			<div class='row'>
				<div class="col">
					<%
					if (request.getAttribute("error") != null) {
					%>
					<p style="color: red"><%=request.getAttribute("error")%></p>
					<%
					}
					%>
				</div>

			</div>

			<button type="submit">Create Account</button>
			<a href="<%=request.getContextPath()%>/Login" style="color: green">Go
				back</a>
		</form>
	</div>
	
</body>


<!-- script for phonenubmer validation -->
<script>
	document.getElementById("myForm").addEventListener("submit",
	function(event) {
		const phoneNumber = document.getElementById("phoneNumber").value;

		// Regular expression for valid phone number formats
		const phoneNumberPattern = /^\+?[0-9\s\-\(\)]+$/;

		if (!phoneNumberPattern.test(phoneNumber)) {
			event.preventDefault(); // Stop form submission
			alert("Please enter a valid phone number.");
		}
	});
</script>
</html>
