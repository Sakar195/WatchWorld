package service;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.DatabaseConnectivity;
import model.product;

public class ProductDao {

	public int insertimage(product Product) throws SQLException {

		Connection conn = DatabaseConnectivity.getDbConnection();
		PreparedStatement st = conn.prepareStatement(
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

		Connection conn = DatabaseConnectivity.getDbConnection();
		PreparedStatement st = conn.prepareStatement("select * from product_details");
		ResultSet set = st.executeQuery();

		List<product> listOfProduct = new ArrayList<>();
		while (set.next()) {
			product image = new product();
			
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

}
