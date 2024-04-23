
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css" />
<style>  
#role {

width:150px;
}

</style>
</head>
<body>
<%@ include file="header.jsp"%>
	<div class="container1">
		<h1>Register</h1>
		<%
			if(request.getAttribute("error1")!=null)
			{
				%>
				<p style="color:red"><%=request.getAttribute("error1") %></p>
				<%
			}
		%>
	
		<form action="<%=request.getContextPath()%>/Registration" method="post">
			<div class="row">
				<div class="col">
					<label for="firstName">First Name:</label> 
					<input type="text" id="firstName" value="${firstName}" name="firstName" required>
				</div>
				<div class="col">
					<label for="lastName">Last Name:</label> 
					<input type="text" id="lastName" value="${lastName}" name="lastName" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="email">Email:</label> 
					<input type="email" id="email" value="${email}" name="email" required>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<label for="gender">Gender:</label> 
					<select id="gender" name="gender" required>
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</div>
				<div class="col">
					<label for="username">Username:</label> 
					<input type="text" id="userName" value="${userName} " name="userName" required>
				</div>
			</div>
			
			<div class="row">
				<div class="col">
					<label for="phoneNumber">Phone Number:</label> 
					<input type="tel" id="phoneNumber" value="${phoneNumber }"name="phoneNumber" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> 
					<input type="password" id="password" name="password" required>
				</div>
				<div class="col">
					<label for="retypePassword">Retype Password:</label> 
					<input	type="password" id="retypePassword" name="retypePassword" required>
				</div>
			</div>
			
			<div class='row'>
				<div class="col">
				<%
					if(request.getAttribute("error")!=null)
					{
						%>
						<p style="color:red"><%=request.getAttribute("error") %></p>
						<%
					}
				%>
				</div>
				
			</div>
			
			<button type="submit">Create Account</button>
		</form>
	</div>
	<div class="container2">
		<h2>This is the registration page</h2>
		<img alt="" src="images/watch.png" width="200" height="200">
	</div>
	<%@ include file="footer.jsp"%>
</body>
</html>
