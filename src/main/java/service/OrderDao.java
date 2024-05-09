package service;

import model.cartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.product;
import utils.DatabaseConnectivity;

public class OrderDao {
	
	private Connection conn;
	private PreparedStatement st;
	private ResultSet rs;

	public OrderDao() {
		conn = DatabaseConnectivity.getDbConnection();
	}

	public boolean addCartItem(int userId, int productId, int quantity) throws SQLException {
		 
		String getCartQuery = "SELECT cart_id FROM cart WHERE user_id = ?";
         int cartId;
         int row = 0;
         System.out.println("entered cart");
         st = conn.prepareStatement(getCartQuery);
         st.setInt(1, userId);
         rs = st.executeQuery();
         if (rs.next())
         {
        	 cartId = rs.getInt("cart_id");
         }
         else {
        
        	
        	 // Create a new cart
        	 String createCartQuery = "INSERT INTO cart (user_id, created_at) VALUES (?, CURRENT_TIMESTAMP)";
        	 
        	 st = conn.prepareStatement(createCartQuery, PreparedStatement.RETURN_GENERATED_KEYS);
        	 st.setInt(1, userId);
        	 st.executeUpdate();//create a new cart
        	 rs = st.getGeneratedKeys();//retrieve generated keus
        	 System.out.println("entered cart");
        	  if (rs.next()) {
                  cartId = rs.getInt(1); // Get the generated cart ID
              } else {
                  return false; // Failed to create a new cart
              }
         }
     
		
         String addCartItemQuery = "INSERT INTO cart_items (cart_id, product_id, quantity, addedDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
         
         st = conn.prepareStatement(addCartItemQuery);
         st.setInt(1, cartId);
         st.setInt(2, productId);
         st.setInt(3, quantity);
         row = st.executeUpdate();
         System.out.println("entered cart");
         if(row>0)
         {
        	 return true;
         }
         
         return false;
	}
	
	// Method to get all items in a user's cart
    public List<cartItem> getCartItems(int userId) throws SQLException {
        List<cartItem> cartItems = new ArrayList<>();

        // Query to get the user's cart ID
        String getCartQuery = "SELECT cart_id FROM cart WHERE user_id = ?";
        st = conn.prepareStatement(getCartQuery);
        st.setInt(1, userId);
        rs = st.executeQuery();

        if (rs.next()) {
            int cartId = rs.getInt("cart_id");

            // Query to get all items in the user's cart
            String getCartItemsQuery = "SELECT product_id, quantity, addedDate FROM cart_items WHERE cart_id = ?";
            st = conn.prepareStatement(getCartItemsQuery);
            st.setInt(1, cartId);

            rs = st.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                LocalDate addedDate = rs.getDate("addedDate").toLocalDate();

                cartItems.add(new cartItem(cartId, productId, quantity, addedDate));
            }
        }

        return cartItems;
    }
    
    
 // Method to close resources to avoid memory leaks
    public void closeResources() {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	
	
	

	

}
