<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Orders</title>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
<style>
/* Styling for the Order page, including buttons */
body {
    font-family: 'Roboto', sans-serif;
    background: linear-gradient(to right, #e3e3e3, #f7f7f7);
    margin: 0;
    padding: 20px;
}

.container {
    width: 90%;
    margin: 0 auto;
    text-align: center;
}

h1 {
    color: #333;
    font-size: 2em;
}

table {
    width: 100%;
    border-collapse: collapse;
    box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    overflow: hidden;
    background: white;
}

th, td {
    padding: 15px;
    text-align: left;
}

th {
    background-color: #6200ea;
    color: white;
    text-transform: uppercase;
    font-weight: 500;
}

tr {
    transition: background-color 0.3s;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}

tr:hover {
    background-color: #e1e1e1;
}

.status {
    padding: 10px 20px;
    border-radius: 20px;
    font-weight: bold;
    display: inline-block;
}

.pending {
    background-color: #ff9800;
    color: white;
}

.approved {
    background-color: #6200ea;
    color: white;
}

.delivered {
    background-color: #34a853;
    color: white;
}

.go-back {
    display: inline-block;
    padding: 12px 25px;
    background-color: #6200ea;
    color: white;
    text-decoration: none;
    border-radius: 15px;
    font-weight: 500;
    cursor: pointer;
    transition: background-color 0.3s;
}

.go-back:hover {
    background-color: #5200c4;
}

.approve-button, .deliver-button {
    padding: 10px 20px;
    border-radius: 10px;
    background-color: #4CAF50;
    color: white;
    font-weight: bold;
    cursor: pointer;
}

.approve-button:hover, .deliver-button:hover {
    background-color: #45a049;
}

.success-message {
    padding: 15px;
    background-color: #d4edda;
    color: #155724;
    border-radius: 10px;
    font-weight: bold;
    margin-bottom: 20px;
}
</style>
</head>
<body>
    <div class="container">
        <h1>All Orders</h1>

        <!-- Display success messages -->
        <c:if test="${param.approval_success == 'true'}">
            <div class="success-message">
                Order approved successfully!
            </div>
        </c:if>

        <c:if test="${param.delivery_success == 'true'}">
            <div class="success-message">
                Order delivered successfully!
            </div>
        </c:if>

        <!-- Go Back button -->
        <a href="<%=request.getContextPath()%>/admin" class="go-back">Go Back</a>

        <table>
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>Customer Name</th>
                    <th>Order Date</th>
                    <th>Total Amount</th>
                    <th>Status</th>
                    <th>Action</th>
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
                        <td>
                            <!-- Render different buttons based on the status -->
                            <c:choose>
                                <c:when test="${order.getStatus() == 'Order Placed'}">
                                    <!-- Approve button -->
                                    <form action="<%=request.getContextPath()%>/Approve" method="post">
                                        <input type="hidden" name="orderId" value="${order.getOrderId()}">
                                        <button type="submit" class="approve-button">Approve</button>
                                    </form>
                                </c:when>
                                <c:when test="${order.getStatus() == 'Approved'}">
                                    <!-- Deliver button -->
                                    <form action="<%=request.getContextPath()%>/Deliver" method="post">
                                        <input type="hidden" name="orderId" value="${order.getOrderId()}">
                                        <button type="submit" class="deliver-button">Deliver</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <!-- No action for delivered orders -->
                                    <span>Dealt With</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
