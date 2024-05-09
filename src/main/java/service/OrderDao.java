package service;

import model.cartItem;
import model.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
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
		System.out.println("entered cart");

		// Check if the cart exists
		st = conn.prepareStatement(getCartQuery);
		st.setInt(1, userId);
		rs = st.executeQuery();

		if (rs.next()) {
			cartId = rs.getInt("cart_id");
		} else {
			// Create a new cart if it doesn't exist
			String createCartQuery = "INSERT INTO cart (user_id, created_at) VALUES (?, CURRENT_TIMESTAMP)";
			st = conn.prepareStatement(createCartQuery, PreparedStatement.RETURN_GENERATED_KEYS);
			st.setInt(1, userId);
			st.executeUpdate();

			rs = st.getGeneratedKeys();
			if (rs.next()) {
				cartId = rs.getInt(1); // Get the generated cart ID
			} else {
				return false; // Failed to create a new cart
			}
		}

		// Check if the product is already in the cart
		String checkProductQuery = "SELECT * FROM cart_items WHERE cart_id = ? AND product_id = ?";
		st = conn.prepareStatement(checkProductQuery);
		st.setInt(1, cartId);
		st.setInt(2, productId);
		rs = st.executeQuery();

		if (rs.next()) {
			System.out.println("Product already exists in the cart");
			return false; // Product is already in the cart
		}

		// Add the product to the cart if it doesn't exist
		String addCartItemQuery = "INSERT INTO cart_items (cart_id, product_id, quantity, addedDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
		st = conn.prepareStatement(addCartItemQuery);
		st.setInt(1, cartId);
		st.setInt(2, productId);
		st.setInt(3, quantity);

		int row = st.executeUpdate();
		if (row > 0) {
			return true; // Product successfully added to the cart
		}

		return false; // Failed to add product to the cart
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
			String getCartItemsQuery = "SELECT ci.product_id, ci.quantity, ci.addedDate, " + "pd.name AS productName, "
					+ "pd.image_data AS productImage, " + "pd.price AS productPrice " + "FROM cart_items ci "
					+ "JOIN product_details pd ON ci.product_id = pd.id " + "WHERE ci.cart_id = ?";

			st = conn.prepareStatement(getCartItemsQuery);
			st.setInt(1, cartId);

			rs = st.executeQuery();

			while (rs.next()) {
				int productId = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				LocalDate addedDate = rs.getDate("addedDate").toLocalDate();
				String productName = rs.getString("productName");

				byte[] imageData = rs.getBytes("productImage");
				String base64ProductImage = Base64.getEncoder().encodeToString(imageData); // Convert to Base64
				int productPrice = rs.getInt("productPrice");

				cartItems.add(new cartItem(cartId, productId, productName, quantity, addedDate, base64ProductImage,
						productPrice));
			}
		}

		return cartItems;
	}

	public void updateCartItemQuantity(int productId, int newQuantity) throws SQLException {

		String updateQuery = "UPDATE cart_items SET quantity = ? WHERE product_id = ?";
		st = conn.prepareStatement(updateQuery);
		st.setInt(1, newQuantity);
		st.setInt(2, productId);
		st.executeUpdate();

	}

	public void deleteCartItem(int userId, int productId) throws SQLException {
		conn.setAutoCommit(false); // Start a transaction

	    try {
	        // First, clear any dependent references (like Order_Items) if needed
	        String deleteOrderItemsQuery = "DELETE FROM Order_Items WHERE Product_Id = ?";
	        st = conn.prepareStatement(deleteOrderItemsQuery);
	        st.setInt(1, productId);
	        st.executeUpdate();

	        // Now, delete from cart_items
	        String deleteCartItemsQuery = "DELETE FROM cart_items WHERE product_id = ? AND cart_id = (SELECT cart_id FROM cart WHERE user_id = ?)";
	        st = conn.prepareStatement(deleteCartItemsQuery);
	        st.setInt(1, productId);
	        st.setInt(2, userId);
	        st.executeUpdate();

	        conn.commit(); // Commit the transaction
	    } catch (SQLException e) {
	        conn.rollback(); // Rollback in case of any error
	        throw e; // Re-throw the exception to indicate failure
	    } finally {
	        conn.setAutoCommit(true); // Reset auto-commit
	    }

	}

	public void createOrder(Integer userId, List<cartItem> cartItems) throws SQLException {

		// insert new order into order table
		String insertOrderQuery = "INSERT INTO Orders (user_id, order_date, status, totalAmount) VALUES (?, CURRENT_TIMESTAMP, 'Order Placed', ?)";
		st = conn.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS);
		double totalAmount = cartItems.stream().mapToDouble(item -> item.getProductPrice() * item.getQuantity()).sum(); // Calculate
																														// total
																														// amount

		st.setInt(1, userId);
		st.setDouble(2, totalAmount);
		st.executeUpdate();

		// Get the generated order_id
		rs = st.getGeneratedKeys();

		int orderId = 0;
		if (rs.next()) {
			orderId = rs.getInt(1); // The newly created Order_id
		}

		// Insert each cart item into the Order_Items table
		String insertOrderItemQuery = "INSERT INTO Order_Items (Order_id, Product_Id, quantity) VALUES (?, ?, ?)";
		st = conn.prepareStatement(insertOrderItemQuery);
		for (cartItem item : cartItems) {
			st.setInt(1, orderId);
			st.setInt(2, item.getProductId());
			st.setInt(3, item.getQuantity());
			st.executeUpdate(); // Insert the order item
		}

		// Step 3: Clear the cart after creating the order
		String clearCartQuery = "DELETE FROM cart_items WHERE cart_id = (SELECT cart_id FROM cart WHERE user_id = ?)";
		st = conn.prepareStatement(clearCartQuery);
		st.setInt(1, userId);
		st.executeUpdate(); // Clear the cart

	}
	public List<order> getAllOrders() throws SQLException {
        List<order> orders = new ArrayList<>();

        String query = "SELECT o.order_id, CONCAT(u.Firstname, ' ', u.Lastname) AS customerName, "
                + "o.order_date, o.totalAmount, o.status "
                + "FROM Orders o "
                + "JOIN user_details u ON o.user_id = u.Id";

        st = conn.prepareStatement(query);
        rs = st.executeQuery();

        while (rs.next()) {
            int orderId = rs.getInt("order_id");
            String customerName = rs.getString("customerName");
            Date orderDate = rs.getDate("order_date");
            double totalAmount = rs.getDouble("totalAmount");
            String status = rs.getString("status");

            // Assuming you have an Order class with a constructor that accepts these arguments
            orders.add(new order(orderId, customerName, orderDate, totalAmount, status));
        }

        return orders;
    }

}
