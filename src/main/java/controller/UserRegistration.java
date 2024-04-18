
package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import service.UserDao;
import utils.PasswordHash;

/**
 * Servlet implementation class UserRegistration
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Registration" })
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserDao userDao;
    @Override
    	public void init(ServletConfig config) throws ServletException {
    		// TODO Auto-generated method stub
    		super.init(config);
    		userDao = new UserDao();
    		
    }
    
    public UserRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(appConstant.MyConstants.REGISTER_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String userName=request.getParameter("userName");
		Long phoneNumber=Long.parseLong(request.getParameter("phoneNumber"));
		String role=request.getParameter("role");
		String password=request.getParameter("password");
		String retypePassword=request.getParameter("retypePassword");
		
		if(!password.equals(retypePassword))
		{
			request.setAttribute("error", "Password doesnot match");
			request.setAttribute("firstName", firstName);
			request.setAttribute("lastName", lastName);
			request.setAttribute("email", email);
			request.setAttribute("userName", userName);
			request.setAttribute("phoneNumber", phoneNumber);
			request.getRequestDispatcher(appConstant.MyConstants.REGISTER_PAGE).forward(request, response);
		}
		
		model.User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setGender(gender);
		user.setUserName(userName);
		user.setPhoneNumber(phoneNumber);
		user.setRole(role);
		user.setPassword(PasswordHash.getPasswordHash(password));
		
		try {
			boolean isSuccess = userDao.saveUser(user);
			
			if(isSuccess)
			{
				request.getRequestDispatcher("Login");
			}
			else 
			{
				request.setAttribute("error1", "Username or email or phonenubmer invalid");
				request.getRequestDispatcher(appConstant.MyConstants.REGISTER_PAGE).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
