package model;

import java.time.LocalDate;

public class cartItem {
	private int cartId;
    private int productId;
    private int quantity;
    private LocalDate addedDate;
	
    public cartItem(int cartId, int productId, int quantity, LocalDate addedDate) {
		super();
		this.cartId = cartId;
		this.productId = productId;
		this.quantity = quantity;
		this.addedDate = addedDate;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}
    
    
    
    

}
