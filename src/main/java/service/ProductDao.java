package service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import utils.DatabaseConnectivity;
import model.product;

public class ProductDao {

	private Connection conn;
	private PreparedStatement st;
	private ResultSet set;

	public ProductDao() {
		conn = DatabaseConnectivity.getDbConnection();
	}

	public int insertimage(product Product) throws SQLException {

		st = conn.prepareStatement(
				"insert into product_details(name,description,image_data,image_name,price,quantity)values(?,?,?,?,?,?)");
		Blob blob = conn.createBlob();

		blob.setBytes(1, Product.getImage_data());

		st.setString(1, Product.getName());
		st.setString(2, Product.getDescription());
		st.setBlob(3, blob);
		st.setString(4, Product.getImage_name());
		st.setInt(5, Product.getPrice());
		st.setInt(6, Product.getQuantity());
		st.setBlob(3, blob);

		int row = st.executeUpdate();
		return row;

	}

	public List<product> getProductDetails() throws SQLException {

		st = conn.prepareStatement("select * from product_details");
		set = st.executeQuery();

		List<product> listOfProduct = new ArrayList<>();
		while (set.next()) {
			product image = new product();

			image.setId(set.getInt("id"));
			image.setName(set.getString("name"));
			image.setDescription(set.getString("description"));
			image.setPrice(set.getInt("price"));
			image.setQuantity(set.getInt("quantity"));

			image.setImage_data(set.getBlob("image_data").getBytes(1, (int) set.getBlob("image_data").length()));
			image.setImage_name(set.getString("image_name"));

			listOfProduct.add(image);

		}
		return listOfProduct;
	}

	public product getProductById(int id) throws SQLException {
		st = conn.prepareStatement(
				"select id,name,description,image_data,image_name,price,quantity from product_details where id=? ");
		st.setInt(1, id);
		set = st.executeQuery();
		product Product = new product();
		if (set.next()) {
			Product.setId(set.getInt("id"));
			Product.setName(set.getString("name"));
			Product.setDescription(set.getString("description"));
			Product.setPrice(set.getInt("price"));
			Product.setQuantity(set.getInt("quantity"));
			Product.setImage_data(set.getBlob("image_data").getBytes(1, (int) set.getBlob("image_data").length()));

			String base64ImageData = Base64.getEncoder().encodeToString(Product.getImage_data());
			Product.setBase64ImageData(base64ImageData);

			Product.setImage_name(set.getString("image_name"));
		} else {
		}

		return Product;

	}

	public int updateProduct(product Product) throws SQLException {
		
		st = conn.prepareStatement("update product_details set name=?, description=?, image_data=?, image_name=?, price=?, quantity=? where id = ?");
		
		Blob blob = conn.createBlob();

		blob.setBytes(1, Product.getImage_data());

		st.setString(1, Product.getName());
		st.setString(2, Product.getDescription());
		st.setBlob(3, blob);
		st.setString(4, Product.getImage_name());
		st.setInt(5, Product.getPrice());
		st.setInt(6, Product.getQuantity());
		st.setInt(7, Product.getId());
	
		
		int row = st.executeUpdate();
		return row;


	}

}
