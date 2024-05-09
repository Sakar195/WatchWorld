<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Orders</title>
<style>
/* Basic styling for the table and other elements */
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f5f5f5;
    margin: 20px;
}

table {
    width: 100%;
    border-collapse: collapse;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Box shadow for modern look */
    border-radius: 10px; /* Rounded corners */
    overflow: hidden; /* Hide overflow for smooth corners */
}

th, td {
    padding: 12px; /* Increased padding for better readability */
    text-align: left;
}

th {
    background-color: #4CAF50;
    color: white;
}

tr:nth-child(even) {
    background-color: #f9f9f9; /* Lighter color for alternating rows */
}

tr:hover {
    background-color: #f1f1f1; /* Hover effect for improved interactivity */
}

.status {
    color: white;
    padding: 8px 15px; /* More padding for emphasis */
    font-weight: bold; /* Bold for emphasis */
    border-radius: 15px; /* Rounded corners for status badge */
}

.pending {
    background-color: #ff9800; /* Orange for pending */
}

.completed {
    background-color: #4CAF50; /* Green for completed */
}

.in-progress {
    background-color: #FFEB3B; /* Yellow for in-progress */
}

/* Style for Go Back button */
.go-back {
    display: inline-block;
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    text-decoration: none;
    border-radius: 10px; /* Rounded corners */
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s; /* Smooth transition for hover effect */
}

.go-back:hover {
    background-color: #45a049; /* Darker green on hover */
}
</style>
</head>
<body>
    <div>
        <h1>All Orders</h1>
        <!-- Go Back button leading to the admin page -->
        <a href="<%=request.getContextPath()%>/admin" class="go-back">Go Back</a>
        
        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Customer Name</th>
                    <th>Order Date</th>
                    <th>Total Amount</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.getOrderId()}</td>
                        <td>${order.getCustomerName()}</td>
                        <td>${order.getOrderDate()}</td>
                        <td>$${order.getTotalAmount()}</td>
                        <td>
                            <span class="status ${order.getStatus().toLowerCase()}">${order.getStatus()}</span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
