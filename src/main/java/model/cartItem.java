package model;

import java.time.LocalDate;

public class cartItem {
    private int cartId;
    private int productId;
    private String productName;
    private int quantity;
    private LocalDate addedDate;
    private String productImage; // Base64-encoded
    private int productPrice; 

    public cartItem(int cartId, int productId, String productName, int quantity, LocalDate addedDate, String productImage, int productPrice) {
        this.cartId = cartId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.addedDate = addedDate;
        this.productImage = productImage;
        this.productPrice = productPrice; // Store the price
    }

    // Getters and other methods
    public int getCartId() {
        return cartId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public String getProductImage() {
        return productImage;
    }

    public int getProductPrice() {
        return productPrice; // Getter for price
    }
}
    
    
