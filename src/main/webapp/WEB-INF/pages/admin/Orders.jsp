<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Order</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	background-color: #f4f4f4;
}

.container {
	max-width: 70%;
	margin: 20px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: left;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 10px;
	border-bottom: 1px solid #ddd;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.status {
	color: #fff;
	padding: 5px 10px;
	border-radius: 5px;
	text-align: center;
}

.pending {
	background-color: #ff9800; /* orange */
}

.completed {
	background-color: #4caf50; /* green */
}
</style>
</head>
<body>
	<div class="container">
		<h1>Order</h1>
		<table>
			<thead>
			<tr>
				<th>Order ID</th>
				<th>Customer Name</th>
				<th>Ordered Items</th>
				<th>Total Price</th>
				<th>Ordered Date</th>
				<th>Status</th>
			</tr>
			</thead>
			<tbody>
				<tr>
					<td>1</td>
					<td>John Doe</td>
					<td>Product A, Product B</td>
					<td>$50.00</td>
					<td>2024-04-28</td>
					<td><span class="status pending">Pending</span></td>
				</tr>
				<tr>
					<td>2</td>
					<td>Jane Smith</td>
					<td>Product C</td>
					<td>$25.00</td>
					<td>2024-04-27</td>
					<td><span class="status completed">Completed</span></td>
				</tr>
				<!-- Add more rows as needed -->
			</tbody>
		</table>
	</div>
</body>
</html>
