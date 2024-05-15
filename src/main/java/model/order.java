package model;

import java.sql.Date;
import java.util.List;

public class order {
    private int orderId;
    private String customerName;
    private Date orderDate;
    private double totalAmount;
    private String status;
    private List<orderItem> orderItems;

    public order(int orderId, String customerName, Date orderDate, double totalAmount, String status, List<orderItem> orderItems) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderItems = orderItems;
    }

    // Getters for each field
    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public List<orderItem> getOrderItems() {
        return orderItems;
    }
}