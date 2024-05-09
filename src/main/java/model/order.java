package model;

import java.sql.Date;

public class order {
	private int orderId;
	private String customerName;
	private Date orderDate;
	private double totalAmount;
	private String status;

	public order(int orderId, String customerName, Date orderDate, double totalAmount, String status) {
		this.orderId = orderId;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
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
}