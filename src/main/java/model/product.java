package model;

public class product {
	
	
	private String image_name;
	private byte[] image_data;
	private String base64ImageData;
	
	private int id;
	private String name;
	private String description;
	private int price;
	private int quantity;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage_name() {
		return image_name;
	}
	public void setImage_name(String image_name) {
		this.image_name = image_name;
	}
	public byte[] getImage_data() {
		return image_data;
	}
	public void setImage_data(byte[] image_data) {
		this.image_data = image_data;
	}
	public String getBase64ImageData() {
		return base64ImageData;
	}
	public void setBase64ImageData(String base64ImageData) {
		this.base64ImageData = base64ImageData;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	

}
