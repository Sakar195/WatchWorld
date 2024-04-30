
package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.MyConstants;
import service.UserDao;
import utils.DatabaseConnectivity;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Login" })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		userDao = new UserDao();

	}

	public UserLogin() {
		super();
		DatabaseConnectivity.getDbConnection();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(MyConstants.LOGIN_PAGE).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginClick = request.getParameter("login-button"); // see if user clicked loginbuton
		List<Integer> userDetails;

		// if users clicks login button
		if (loginClick != null) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("success till here1");

			try {

				userDetails = userDao.userLogin(username, password);
				int login_value = userDetails.get(0);
				int role_id = userDetails.get(1);
				System.out.println("success till here1");

				// if username and password matched
				if (login_value == 1) {

					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					session.setAttribute("role", role_id);
					session.setAttribute("login_value", login_value);
					session.setMaxInactiveInterval(30 * 60);
					System.out.println("success till here1");
					// if user is admin
					if (role_id == 1) {
						request.getRequestDispatcher(MyConstants.ADMIN_PAGE).forward(request, response);
					}
					// if user is normal user
					else {
						request.getRequestDispatcher(MyConstants.HOME_PAGE).forward(request, response);
					}
					// if password does not match in database
				} else if (login_value == 0) {
					request.setAttribute("error", "Invalid password");
					request.setAttribute("username", username);
					doGet(request, response);
					System.out.println("Login Failed");
				}
				// both username and password incorrect
				else {
					request.setAttribute("error", "Account Doesn't exist");
					doGet(request, response);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Error in login");
			}

		}
		else
		{
			//request.setAttribute("error", "Not entering Login");
			doGet(request, response);
		}

	}

}
