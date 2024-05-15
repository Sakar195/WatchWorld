package service;

import model.cartItem;
import model.order;
import model.orderItem;

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
			System.out.println("Product already exists in the cart11111111");
			return false; // Product is already in the cart
		}

		// Add the product to the cart if it doesn't exist
		String addCartItemQuery = "INSERT INTO cart_items (cart_id, product_id, quantity, addedDate) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
		System.out.println("adding existing product");
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
				+ "o.order_date, o.totalAmount, o.status " + "FROM Orders o "
				+ "JOIN user_details u ON o.user_id = u.Id";

		st = conn.prepareStatement(query);
		rs = st.executeQuery();

		while (rs.next()) {
			int orderId = rs.getInt("order_id");
			String customerName = rs.getString("customerName");
			Date orderDate = rs.getDate("order_date");
			double totalAmount = rs.getDouble("totalAmount");
			String status = rs.getString("status");

			// Assuming you have an Order class with a constructor that accepts these
			// arguments
			orders.add(new order(orderId, customerName, orderDate, totalAmount, status,null));
		}

		return orders;
	}

	public List<cartItem> getOrderItemsByOrderId(int orderId) throws SQLException {
		List<cartItem> orderItems = new ArrayList<>();

		String query = "SELECT oi.Product_Id, oi.quantity, pd.name, pd.price " + "FROM Order_Items oi "
				+ "JOIN product_details pd ON oi.Product_Id = pd.id " + "WHERE oi.Order_id = ?";

		st = conn.prepareStatement(query);
		st.setInt(1, orderId);
		rs = st.executeQuery();

		while (rs.next()) {
			int productId = rs.getInt("Product_Id");
			int quantity = rs.getInt("quantity");
			String productName = rs.getString("name");
			int productPrice = rs.getInt("price");

			orderItems.add(new cartItem(0, productId, productName, quantity, null, null, productPrice));
		}

		return orderItems;
	}

	public void approveOrder(int orderId) throws SQLException {
		String updateQuery = "UPDATE orders SET status = 'Approved' WHERE order_id = ?";
		st = conn.prepareStatement(updateQuery);
		st.setInt(1, orderId);
		st.executeUpdate();
	}

	public void deliverOrder(int orderId) throws SQLException {
		String updateStatusQuery = "UPDATE orders SET status = 'delivered' WHERE order_id = ?";
		st = conn.prepareStatement(updateStatusQuery);
		st.setInt(1, orderId);
		st.executeUpdate();

	}

	public List<order> getOrdersByUserId(int userId) throws SQLException {
		List<order> userOrders = new ArrayList<>();

		String query = "SELECT o.order_id, o.order_date, o.totalAmount, o.status, "
				+ "(SELECT GROUP_CONCAT(oi.product_id SEPARATOR ',') FROM Order_Items oi WHERE oi.order_id = o.order_id) AS product_ids, "
				+ "(SELECT GROUP_CONCAT(oi.quantity SEPARATOR ',') FROM Order_Items oi WHERE oi.order_id = o.order_id) AS quantities "
				+ "FROM Orders o " + "WHERE o.user_id = ?";

		st = conn.prepareStatement(query);
		st.setInt(1, userId);
		rs = st.executeQuery();

		while (rs.next()) {
			int orderId = rs.getInt("order_id");
			Date orderDate = rs.getDate("order_date");
			double totalAmount = rs.getDouble("totalAmount");
			String status = rs.getString("status");
			String productIdsString = rs.getString("product_ids");
			String quantitiesString = rs.getString("quantities");

			// Convert product IDs and quantities from strings to arrays
			String[] productIds = productIdsString != null ? productIdsString.split(",") : new String[0];
			String[] quantities = quantitiesString != null ? quantitiesString.split(",") : new String[0];

			// Create a list to store order items for the current order
			List<orderItem> orderItems = null;

			if (productIds.length == quantities.length) {
				// Populate the order items list only if productIds and quantities have the same
				// length
				orderItems = new ArrayList<>();

				// Populate the order items list
				for (int i = 0; i < productIds.length; i++) {
					int productId = Integer.parseInt(productIds[i]);
					int quantity = Integer.parseInt(quantities[i]);

					// You may need to retrieve product details from the database based on productId
					// For simplicity, I'm creating a dummy cartItem object with just the productId
					// and quantity
					orderItem item = new orderItem(productId, quantity);
					orderItems.add(item);
				}
			}

			// Create an order object with order details and order items
			order userOrder = new order(orderId, null, orderDate, totalAmount, status, orderItems);
			userOrders.add(userOrder);
		}

		return userOrders;
	}

}
