package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.DatabaseConnectivity;

public class FeedbackDao {
	
	private Connection conn;
	private PreparedStatement st;
	public FeedbackDao() {
		conn = DatabaseConnectivity.getDbConnection();
	}

	public void saveFeedback(String email, String phoneNumber, String message) throws SQLException {
		// TODO Auto-generated method stub
		 String insertFeedbackQuery = "INSERT INTO feedback (email, phonenumber, message) VALUES (?, ?, ?)";
		 st = conn.prepareStatement(insertFeedbackQuery);
		 st.setString(1, email);
		 st.setString(2, phoneNumber);
		 st.setString(3, message);
		 st.executeUpdate(); // Execute the insertion
	}

}
