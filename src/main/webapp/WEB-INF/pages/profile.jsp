<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profile</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/profile.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Include jQuery for convenience -->

<script src="<%=request.getContextPath()%>/js/script.js"></script>
<script>
	$(document).ready(function() {
		// Initialize all forms' visibility
		$(".change-password-form").hide();
		$(".account").show();

		// Click event for the "Account" menu item
		$(".account-button").click(function(e) {
			e.preventDefault();
			$(".account").show();
			$(".change-password-form").hide();

			// Handle active class
			$(".menu-link").removeClass("active");
			$(this).addClass("active");
		});

		// Click event for the "Change Password" menu item
		$(".change-password-button").click(function(e) {
			e.preventDefault();
			$(".account").hide();
			$(".change-password-form").show();

			// Handle active class
			$(".menu-link").removeClass("active");
			$(this).addClass("active");
		});

		// Click event for other menu items
		$(".menu-link").click(function(e) {
			// Handle active class for other menu links
			$(".menu-link").removeClass("active");
			$(this).addClass("active");
		});
	});

	function validateForm() {
		var inputs = document
				.querySelectorAll('.account input[type="text"], .account input[type="email"], .account input[type="tel"], .account input[type="password"], .account select');
		for (var i = 0; i < inputs.length; i++) {
			if (inputs[i].value.trim() === '') {
				alert('Please fill in all fields.');
				return false; // Prevent form submission
			}
		}
		return true; // Allow form submission if all fields are filled
	}
</script>
</head>
<body>
	<%@ include file="includes/header.jsp"%>
	<c:set var="user" value="${user}"></c:set>
	<div class="container">
		<div class="profile">
			<div class="profile-header">

				<div class="profile-text-container">
					<h1 class="profile-title">${sessionScope.username}</h1>
				</div>
			</div>

			<div class="menu">
				<a href="<%=request.getContextPath()%>/Profile?method=get"
					class="menu-link account-button active">Account</a>

				<c:if test="${sessionScope.role_id == 1}">
					<a href="<%=request.getContextPath()%>/Orders" class="menu-link">Orders</a>
				</c:if>

				<a href="#" class="menu-link change-password-button">Change
					Password</a>
				<!-- Change Password link -->

				<a href="#" class="menu-link">Payment Option</a>

				<c:if test="${sessionScope.role_id != 1}">
					<a href="<%=request.getContextPath()%>/UserOrders" class="menu-link">History</a>
				</c:if>
				<c:if test="${sessionScope.role_id == 1}">
					<a href="<%=request.getContextPath()%>/admin" class="menu-link">Admin
						Panel</a>
				</c:if>

				<!-- This is the logout link -->
				<form action="<%=request.getContextPath()%>/Logout" method="post">
					<button type="submit" class="menu-link logout-button"
						onclick="confirmLogout(event)">Logout</button>
				</form>
			</div>
		</div>

		<form class="account" action="<%=request.getContextPath()%>/Profile"
			method="post">
			<div class="account-header">
				<h1 class="account-title">Account Settings</h1>
				<div class="btn-container">
					<!-- Change to GET and submit for Cancel -->
					<button type="button" class="btn-cancel"
						onclick="this.form.method = 'GET'; this.form.submit();">Cancel</button>
					<button type="button" class="btn-save"
						onclick="this.form.method = 'POST'; this.form.submit();">Save</button>
				</div>
			</div>

			<div class="account-edit">
				<div class="input-container">
					<label for="firstName">First Name:</label> <input type="text"
						id="firstName" value="${user.firstName}" name="firstName"
						placeholder="First Name" required>
				</div>

				<div class="input-container">
					<label for="lastName">Last Name:</label> <input type="text"
						id="lastName" value="${user.lastName}" name="lastName"
						placeholder="Last Name" required>
				</div>
			</div>

			<div class="account-edit">
				<div class="input-container">
					<label for="email">Email:</label> <input type="email" id="email"
						value="${user.email}" name="email" placeholder="Email" required>
				</div>

				<div class="input-container">
					<label for="phoneNumber">Phone Number:</label> <input type="tel"
						id="phoneNumber" value="${user.phoneNumber }" name="phoneNumber"
						placeholder="Phone number" required>
				</div>
			</div>

			<div class="account-edit">
				<div class="input-container">
					<label for="gender">Gender:</label> <select id="gender"
						name="gender" required
						style="height: 40px; padding: 10px; font-size: 16px;">
						<option value="male" ${user.gender == 'male' ? 'selected' : ''}>Male</option>
						<option value="female"
							${user.gender == 'female' ? 'selected' : ''}>Female</option>
					</select>
					<!-- Debugging -->

				</div>
				<div class="input-container">
					<label for="username">Username:</label> <input type="text"
						id="userName" value="${user.userName} " name="userName"
						placeholder="UserName" required>
				</div>
			</div>
			<div class="account-edit">
				<div class="input-container">
					<label for="email">Address:</label> <input type="text" id="address"
						value="${user.address}" name="address" placeholder="Address"
						required>

				</div>
			</div>
			<%
			if (request.getAttribute("error") != null) {
			%>
			<p style="color: red"><%=request.getAttribute("error")%></p>
			<%
			} else if (request.getAttribute("message") != null) {
			%>
			<p style="color: green"><%=request.getAttribute("message")%></p>
			<%
			}
			%>
		</form>
		<c:choose>
			<c:when test="${errorSource == 'change-password'}">
				<script>
					$(document).ready(function() {
						// Hide the account form and show the change password form
						$(".account").hide();
						$(".change-password-form").show();

						// Set active menu item to change password
						$(".menu-link").removeClass("active");
						$(".change-password-button").addClass("active");
					});
				</script>
			</c:when>
			<c:otherwise>
				<script>
					$(document).ready(function() {
						// Show the account form by default
						$(".account").show();
						$(".change-password-form").hide();

						// Set active menu item to account
						$(".menu-link").removeClass("active");
						$(".account-button").addClass("active");
					});
				</script>
			</c:otherwise>
		</c:choose>

		<!-- Change Password Form (Initially hidden) -->
		<form class="change-password-form"
			action="<%=request.getContextPath()%>/CPassword" method="post"
			style="display: none;">
			<!-- Hidden initially -->
			<div class="account-header">
				<h1 class="account-title">Change Password</h1>
				<div class="btn-container">
					<button class="btn-save">Change</button>
				</div>
			</div>

			<div class="account-edit">
				<div class="input-container">
					<label for="oldPassword">Old Password:</label> <input
						type="password" id="oldPassword" name="oldPassword"
						value="${oldPassword}" required>
				</div>
			</div>

			<div class="account-edit">
				<div class="input-container">
					<label for="newPassword">New Password:</label> <input
						type="password" id="newPassword" name="newPassword" required>
				</div>
			</div>

			<div class="account-edit">
				<div class="input-container">
					<label for="confirmPassword">Confirm Password:</label> <input
						type="password" id="confirmPassword" name="confirmPassword"
						required>
				</div>
			</div>
			<!-- Display error message if present -->
			<c:if test="${not empty errorMessage}">
				<p style="color: red">
					<c:out value="${errorMessage}" />
				</p>
			</c:if>
			<c:if test="${not empty message1}">
				<p style="color: green">
					<c:out value="${message1}" />
				</p>
			</c:if>

		</form>
	</div>
</body>
</html>