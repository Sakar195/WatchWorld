<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Orders</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <%@ include file="includes/header.jsp"%>
    <!-- Header section -->
    <div style="height: 80px;"></div>

    <div class="cart-container">
        <h1>User Orders</h1>

        <c:choose>
            <c:when test="${not empty userOrders}">
                <table class="cart-items-table">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Order Date</th>
                            <th>Total Amount</th>
                            <th>Status</th>
                            <th>Product IDs</th>
                            <th>Quantities</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="userOrder" items="${userOrders}">
                            <c:forEach var="orderItem" items="${userOrder.orderItems}">
                                <tr>
                                    <td>${userOrder.orderId}</td>
                                    <td>${userOrder.orderDate}</td>
                                    <td>${userOrder.totalAmount}</td>
                                    <td>${userOrder.status}</td>
                                    <td>${orderItem.productId}</td>
                                    <td>${orderItem.quantity}</td>
                                </tr>
                            </c:forEach>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No orders found.</p>
            </c:otherwise>
        </c:choose>
        <a href="<%=request.getContextPath()%>/Profile" style="color: green">Go back</a>
    </div>
</body>
</html>
