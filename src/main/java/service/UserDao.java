package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;
import utils.DatabaseConnectivity;
import utils.PasswordHash;

public class UserDao {
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultSet;

	public UserDao() {
		this.conn = DatabaseConnectivity.getDbConnection();
	}

	// checking method verificaion
	public int saveUser(User user) throws SQLException {
		String query = "select count(*) from user_details";
		statement = conn.prepareStatement(query);
		resultSet = statement.executeQuery();

		int success = 0;
		if (resultSet.next()) {
			int find = checkUser(user);
			if (find == 1) {
				success = 1;
			} else if (find == 2) {
				success = 2;
			} else if (find == 3) {
				success = 3;
			} else {
				int row = insertUserData(user);
				if (row > 0) {
					success = 0;
				}
			}

		}

		else {
			int row = insertUserData(user);
			if (row > 0) {
				success = 0;
			}
		}
		return success;
	}

	// method to check if the data/user already exists

	public int checkUser(User user) throws SQLException {
		String query = "select Username,Email,Phonenumber from user_details";
		statement = conn.prepareStatement(query);
		resultSet = statement.executeQuery();
		System.out.println("Saving userdao: " + user);
		int find = 0;
		while (resultSet.next()) {

			if (user.getUserName().equals(resultSet.getString("Username"))) {
				find = 1;
				break;
			} else if (user.getEmail().equals(resultSet.getString("Email"))) {
				find = 2;
				break;
			} else if (user.getPhoneNumber() == resultSet.getLong("Phonenumber")) {
				find = 3;
				break;
			}

		}
		return find;
	}

	// method to insert data into the database
	public int insertUserData(User user) throws SQLException {
		String query = "insert into user_details(Firstname,Lastname,Email,Address,Gender,Username,Phonenumber,Password) values(?,?,?,?,?,?,?,?)";
		statement = conn.prepareStatement(query);
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getAddress());
		statement.setString(5, user.getGender());
		statement.setString(6, user.getUserName());
		statement.setLong(7, user.getPhoneNumber());
		statement.setString(8, user.getPassword());

		int row = statement.executeUpdate();

		return row;
	}

	public List<Integer> userLogin(String username, String password) throws SQLException {

		List<Integer> userDetails = new ArrayList<Integer>();
		int login_value = 0;
		int role_id = 0;
		int id = 0;

		statement = conn.prepareStatement("select Id ,Username,Password,role_id from user_details where Username=?");
		statement.setString(1, username);
		resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String hashPasswordFromDb = resultSet.getString("Password");
			id = resultSet.getInt("Id");
			role_id = resultSet.getInt("role_id");

			if (PasswordHash.checkPassword(password, hashPasswordFromDb)) {
				login_value = 1;
			} else {
				login_value = 0;
			}

		} else {
			login_value = 10;
		}

		userDetails.add(login_value);
		userDetails.add(role_id);
		userDetails.add(id);

		return userDetails;

	}

	public User getUserById(int id) throws SQLException {
		statement = conn.prepareStatement(
				"select Firstname,Lastname,Email,Address,Gender,Username,Phonenumber from user_details where id=?");
		statement.setInt(1, id);
		resultSet = statement.executeQuery();
		User user = new User();
		if (resultSet.next()) {

			user.setFirstName(resultSet.getString("Firstname"));
			user.setLastName(resultSet.getString("Lastname"));
			user.setEmail(resultSet.getString("Email"));
			user.setAddress(resultSet.getString("Address"));
			user.setUserName(resultSet.getString("Username"));
			user.setGender(resultSet.getString("Gender"));
			user.setPhoneNumber(resultSet.getLong("Phonenumber"));

		}
		return user;

	}

	public List<User> getAllUser() throws SQLException {
		statement = conn.prepareStatement("select* from user_details");
		resultSet = statement.executeQuery();
		List<User> listOfUser = new ArrayList<User>();

		while (resultSet.next()) {
			User user = new User();
			user.setFirstName(resultSet.getString("Firstname"));
			user.setLastName(resultSet.getString("Lastname"));
			user.setUserName(resultSet.getString("Email"));
			user.setGender(resultSet.getString("Gender"));
			user.setEmail(resultSet.getString("Username"));
			user.setPhoneNumber(resultSet.getLong("Phonenumber"));
			listOfUser.add(user);

		}
		return listOfUser;
	}

	public String updateUser(User user) throws SQLException {
		int row = 0;
		String result = "fail";

		if (isUsernameTakenByOther(user.getUserName(), user.getId())) {
			result = "UsernameTakenByOther";
			return result;
		} else if (isEmailTakenByOther(user.getEmail(), user.getId())) {
			result = "EmailTakenByOther";
			return result;
		} else if (isPhoneNumberTakenByOther(user.getPhoneNumber(), user.getId())) {
			result = "PhoneNumberTakenByOther";
			return result;
		} else {
			statement = conn.prepareStatement(
					"update user_details set Firstname=?,Lastname=?,Email=?,Address=?,Gender=?,Username=?,Phonenumber=? where Id=?");

			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getAddress());
			statement.setString(5, user.getGender());
			statement.setString(6, user.getUserName());
			statement.setLong(7, user.getPhoneNumber());
			statement.setInt(8, user.getId());

			row = statement.executeUpdate();
			if (row > 0) {
				result = "success";
			}
		}

		return result;
	}

	private boolean isPhoneNumberTakenByOther(long phoneNumber, int id) throws SQLException {
		statement = conn
				.prepareStatement("select count(*) as count_id from user_details where Phonenumber=? and Id!=?");
		statement.setLong(1, phoneNumber);
		statement.setInt(2, id);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			int row_number = resultSet.getInt("count_id");
			if (row_number > 0) {
				return true;
			}
		}
		return false;
	}

	private boolean isEmailTakenByOther(String email, int id) throws SQLException {
		// TODO Auto-generated method stub
		statement = conn.prepareStatement("select count(*) as count_id from user_details where Email=? and Id!=?");
		statement.setString(1, email);
		statement.setInt(2, id);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			int row_number = resultSet.getInt("count_id");
			if (row_number > 0) {
				return true;
			}
		}
		return false;
	}

	private boolean isUsernameTakenByOther(String username, int id) throws SQLException {
		// TODO Auto-generated method stub
		statement = conn.prepareStatement("select count(*) as count_id from user_details where Username=? and Id != ?");
		statement.setString(1, username);
		statement.setInt(2, id);

		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			int row_number = resultSet.getInt("count_id");
			if (row_number > 0) {
				return true;
			}

		}
		return false;
	}

	public boolean changePassword(int userId, String oldPassword, String newPassword) throws SQLException {

		statement = conn.prepareStatement("SELECT Password FROM user_details WHERE Id = ?");
		statement.setInt(1, userId);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			String storedPasswordHash = resultSet.getString("Password");

			// Check if the provided oldPassword matches the stored hashed password
			if (PasswordHash.checkPassword(oldPassword, storedPasswordHash)) {
				// If passwords match, update the password with the new hashed password
				String newPasswordHash = PasswordHash.getPasswordHash(newPassword);

				// Update the password in the database
				String updateQuery = "UPDATE user_details SET Password = ? WHERE Id = ?";
				PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
				updateStatement.setString(1, newPasswordHash);
				updateStatement.setInt(2, userId);

				// Execute the update statement
				int row_number= updateStatement.executeUpdate();
				if (row_number > 0) {
					return true;
				}
				
			}
			else {
		        System.out.print("User with ID " + userId + " not found.");
		    }
		}
		return false;

	}
	
	// Method to close resources to avoid memory leaks
    public void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
