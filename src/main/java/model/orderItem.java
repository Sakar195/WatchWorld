package model;

public class orderItem {
    private int productId;
    private int quantity;

    public orderItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getters for productId and quantity
    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
}