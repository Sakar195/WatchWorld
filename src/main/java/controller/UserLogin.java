
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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(MyConstants.LOGIN_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginClick = request.getParameter("login-button"); // see if user clicked loginbuton
		List<Integer> userDetails;

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
					session.setMaxInactiveInterval(30 * 60);
					System.out.println("success till here1");
					// if user is admin
					if (role_id == 1) {
						request.setAttribute("login_value", login_value);
						request.getRequestDispatcher(MyConstants.ADMIN_PAGE).forward(request, response);
					}
					// if user is normal user
					else {
						request.setAttribute("login_value", login_value);
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

//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		String loginClick = request.getParameter("login-button");
//		int login_value = 0;
//
//		if (loginClick != null) {
//			String username = request.getParameter("username");
//			String password = request.getParameter("password");
//			System.out.println("success till here1");
//
//			try {
//
//				login_value = userDao.userLogin(username, password);
//				System.out.println("success till here2");
//
//				if (login_value == 1) {
//					System.out.println("success till here33");
//					st = conn.prepareStatement("select Username, role_id from user_details where Username=?");
//					st.setString(1, username);
//					rs = st.executeQuery();
//					System.out.println("success till here3");
//
//					if (rs.next()) {
//						System.out.println("success till here44");
//						int role_id = rs.getInt("role_id");
//						System.out.println("success till here4"); 
//
//						HttpSession session = request.getSession();
//						session.setAttribute("username", username);
//						session.setAttribute("role", role_id);
//						session.setMaxInactiveInterval(30 * 60);
//
//						if (role_id == 1) {
//							request.setAttribute("login_value", login_value);
//							request.getRequestDispatcher(MyConstants.ADMIN_PAGE).forward(request, response);
//						} else {
//							request.setAttribute("login_value", login_value);
//							request.getRequestDispatcher(MyConstants.HOME_PAGE).forward(request, response);
//						}
//					}
//
//				} else if(login_value == 0){
//					request.setAttribute("error", "Invalid password");
//					request.setAttribute("username", username);
//					request.getRequestDispatcher(MyConstants.LOGIN_PAGE).forward(request, response);
//					System.out.println("Login Failed");
//				}
//				else {
//					request.setAttribute("error", "Account Doesn't exist");
//					request.getRequestDispatcher(MyConstants.LOGIN_PAGE).forward(request, response);
//				}
//
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				System.out.println("Error in login");
//			}
//
//		} else {
//			request.setAttribute("error", "not entering login");
//			request.getRequestDispatcher(MyConstants.LOGIN_PAGE).forward(request, response);
//		}
//		//doGet(request, response);
//	}

}
