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
	boolean isSuccess=false;
	
	
	
	public UserDao()
	{
		this.conn=DatabaseConnectivity.getDbConnection();
	}
	
	//checking method verificaion
	public boolean saveUser(User user) throws SQLException 
	{
		String query="select count(*) from user_details";
		statement=conn.prepareStatement(query);
		resultSet= statement.executeQuery();
		
		
		if(resultSet.next())
		{
			boolean isFind=checkUser(user);
			if(isFind)
			{
				isSuccess=false;
			}
			else
			{
				int row=insertUserData(user);
				if(row>0)
				{
					isSuccess=true;
				}
			}
				
		}
		
		else
		{
			int row=insertUserData(user);
			if(row>0)
			{
				isSuccess=true;
			}
		}
		return isSuccess;
	}
	
	// method to check if the data/user already exists
	
	public boolean checkUser(User user) throws SQLException {
		String query = "select Username,Email,Phonenumber from user_details";
		statement=conn.prepareStatement(query);
		resultSet=statement.executeQuery();
		boolean isFind=false;
		while(resultSet.next())
		{
			String usernameFromDB=resultSet.getString("Username");
			String emailFromDB=resultSet.getString("Email");
			Long phoneNumberFromDB=resultSet.getLong("Phonenumber");
			if(user.getUserName().equals(usernameFromDB)|| user.getEmail().equals(emailFromDB) || user.getPhoneNumber()==phoneNumberFromDB)
			{
				isFind=true;
				break;
			}
			
		}
		return isFind;
	}

	// method to insert data into the database 
	public int insertUserData(User user) throws SQLException
	{
		String query="insert into user_details(Firstname,Lastname,Email,Gender,Username,Phonenumber,Role,Password) values(?,?,?,?,?,?,?,?)";
		statement=conn.prepareStatement(query);
		statement.setString(1, user.getFirstName());
		statement.setString(2, user.getLastName());
		statement.setString(3, user.getEmail());
		statement.setString(4, user.getGender());
		statement.setString(5, user.getUserName());
		statement.setLong(6, user.getPhoneNumber());
		statement.setString(8, user.getPassword());
		
		int row = statement.executeUpdate();
		
		return row;
	}
	public boolean userLogin(String username, String password) throws SQLException
	{
		statement = conn.prepareStatement("select username,password,role_id from student_register where username=?");
		statement.setString(1, username);
		resultSet=statement.executeQuery();
		boolean isLogin=false;
		if(resultSet.next())
		{
			String hashPasswordFromDb=resultSet.getString("Password");
			if(PasswordHash.checkPassword(password, hashPasswordFromDb))
			{
				isLogin = true;
			}
			else
			{
				isLogin = false;
			}
			
		}
		return isLogin;
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