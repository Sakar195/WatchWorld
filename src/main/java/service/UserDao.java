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

	
	
	
	public UserDao()
	{
		this.conn=DatabaseConnectivity.getDbConnection();
	}
	
	//checking method verificaion
	public int saveUser(User user) throws SQLException 
	{
		String query="select count(*) from user_details";
		statement=conn.prepareStatement(query);
		resultSet= statement.executeQuery();
		
		int success = 0;
		if(resultSet.next())
		{
			int find=checkUser(user);
			if(find == 1)
			{
				success=1;
			}
			else if(find == 2)
			{
				success=2;
			}
			else if(find == 3)
			{
				success=3;
			}
			else
			{
				int row=insertUserData(user);
				if(row>0)
				{
					success=0;
				}
			}
				
		}
		
		else
		{
			int row=insertUserData(user);
			if(row>0)
			{
				success=0;
			}
		}
		return success;
	}
	
	// method to check if the data/user already exists
	
	public int checkUser(User user) throws SQLException {
		String query = "select Username,Email,Phonenumber from user_details";
		statement=conn.prepareStatement(query);
		resultSet=statement.executeQuery();
		System.out.println("Saving userdao: " + user);
		int find = 0;
		while(resultSet.next())
		{
						
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
	public int insertUserData(User user) throws SQLException
	{
		String query="insert into user_details(Firstname,Lastname,Email,Gender,Username,Phonenumber,Password) values(?,?,?,?,?,?,?)";
		statement=conn.prepareStatement(query);
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getGender());
		statement.setString(5, user.getUserName());
		statement.setLong(6, user.getPhoneNumber());
		statement.setString(7, user.getPassword());
		
		int row = statement.executeUpdate();
		
		return row;
	}
	public int userLogin(String username, String password) throws SQLException
	{
		statement = conn.prepareStatement("select Username,Password,role_id from user_details where Username=?");
		statement.setString(1, username);
		resultSet=statement.executeQuery();
		int login_value=0;
		if(resultSet.next())
		{
			String hashPasswordFromDb=resultSet.getString("Password");
			if(PasswordHash.checkPassword(password, hashPasswordFromDb))
			{
				login_value = 1;
			}
			else
			{
				login_value = 0;
			}
			
		}
		else {
			login_value = 10;
		}
		return login_value;
	}
	
	public List<Object> user2Login(String username, String password) throws SQLException
	{
		statement = conn.prepareStatement("select Username,Password,role_id from user_details where Username=?");
		statement.setString(1, username);
		resultSet=statement.executeQuery();
		List<Object> userDetails = new ArrayList<Object>();
		boolean isLogin=false;
		if(resultSet.next())
		{
			String hashPasswordFromDb=resultSet.getString("Password");
			int role_id = resultSet.getInt("role_id");
			
			if(PasswordHash.checkPassword(password, hashPasswordFromDb))
			{
				isLogin = true;
				userDetails.add(isLogin);
				userDetails.add(role_id);
			}
			
			else
			{
				isLogin = false;
				userDetails.add(isLogin);
				
			}
			
					
		}
		return userDetails;
	}
	
	public List<User> getAllUser() throws SQLException {
		statement=conn.prepareStatement("select* from user_details");
		resultSet=statement.executeQuery();
		List<User> listOfUser=new ArrayList<User>();
		
		while(resultSet.next())
		{
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

}