<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>View Products</title>

<!-- Inter CSS styling -->
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
	padding: 0;
	background-color: #f5f5f5;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #4CAF50;
	color: white;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

tr:hover {
	background-color: #ddd;
}

.description {
	max-width: 200px; /* Restrict the width of the description column */
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
}

button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 5px 10px;
	cursor: pointer;
}

button:hover {
	background-color: #45a049;
}

.big-font {
	font-size: 1.5rem;
	color: green;
}
</style>
</head>
<body>
	<h1>This is the view product page</h1>
	<%
	int id = 1;
	if (session.getAttribute("message") != null) {
	%>
	<p class="big-font"><%=session.getAttribute("message")%></p>
	<%
	}
	%>
	<%
	session.removeAttribute("message");
	%>
	<table border="1">
		<thead>
			<tr>
				<th>S.N.</th>
				<th>Name</th>
				<th>Description</th>
				<th>Image</th>
				<th>Image_name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:set var="id" value="1" />
			<c:forEach var="product" items="${listOfProduct }">
				<tr>
					<td>${id}</td>
					<td><c:out value="${product.name}"></c:out></td>
					<td style="max-width: 150px;"><c:out
							value="${product.description }"></c:out></td>
					<td><img src="data:image/*;base64,${product.base64ImageData}"
						height="100px" width="100px" alt=""></td>
					<td><c:out value="${product.image_name }"></c:out></td>
					<td><c:out value="${product.price }"></c:out></td>
					<td><c:out value="${product.quantity }"></c:out></td>


					<td><form action="<%=request.getContextPath()%>/Edit"
							method="get">
							
							<input type="hidden" name="id" value="${product.id}">
							<button type="submit">Edit</button>
						</form></td>
					<td><form action="<%=request.getContextPath()%>/Delete"
							method="post">
							<input type="hidden" name="id" value="${product.id}">
							<button type="submit">Delete</button>
						</form></td>
				</tr>
				<c:set var="id" value="${id + 1}" />
			</c:forEach>
		</tbody>
	</table>
	<a href="<%=request.getContextPath()%>/admin" style="color: green">Go
		Back</a>
</body>
</html>