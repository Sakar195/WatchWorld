<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profile</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/profile.css">
</head>
<body>
<%@ include file="includes/header.jsp"%>
	<div class="container">
		<div class="profile">
			<div class="profile-header">
				<img src="profile.png" alt="profile" class="profile-img">
				<div class="profile-text-container">
					<h1 class="profile-title">User Name</h1>
					<p class="profile-email">email123@gmail.com</p>
				</div>
			</div>

			<div class="menu">
				<a href="#" class="menu-link">Account</a>
				<!--icon halam-->
				<a href="#" class="menu-link">Order</a>
				<!--icon halam-->
				<a href="#" class="menu-link">Notification</a>
				<!--icon halam-->
				<a href="#" class="menu-link">Payment Option</a>
				<!--icon halam-->
				<a href="#" class="menu-link">History</a>
				<!--icon halam-->
				<a href="#" class="menu-link">Logout</a>
				<!--icon halam-->
			</div>
		</div>

		<form class="account">
			<div class="account-header">
				<h1 class="account-title">Account Settings</h1>
				<div class="btn-container">
					<button class="btn-cancel">Cancel</button>
					<button class="btn-save">Save</button>
				</div>
			</div>

			<div class="account-edit">
				<div class="input-container">
					<label>First Name</label> <input type="text"
						placeholder="First Name" />
				</div>

				<div class="input-container">
					<label>Last Name</label> <input type="text" placeholder="Last Name" />
				</div>
			</div>

			<div class="account-edit">
				<div class="input-container">
					<label>Email</label> <input type="email" placeholder="Email" />
				</div>

				<div class="input-container">
					<label>Phone Number</label> <input type="text"
						placeholder="Phone Number" />
				</div>
			</div>
			<div class="account-edit">
				<div class="input-container">
					<label>Address</label>
					<textarea placeholder="Address"></textarea>
				</div>
			</div>
		</form>
	</div>
</body>
</html>