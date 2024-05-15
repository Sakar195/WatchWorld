package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import appConstant.MyConstants;
import model.User;
import service.UserDao;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Profile" })
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao = new UserDao();
	}

	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		// String username = (String) session.getAttribute("username");

		try {
			Integer id = (Integer) session.getAttribute("userId");
			if (id == null) {
				response.sendRedirect(request.getContextPath() + "/Login");
				return;
			}
			User user = dao.getUserById(id);
			session.setAttribute("user", user); // Set user object in session
			request.setAttribute("user", user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error in forwarding user details");
		}

		request.getRequestDispatcher(MyConstants.PROFILE_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Integer id = (Integer) session.getAttribute("userId");

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String userName = request.getParameter("userName");
		String phoneNumberStr = request.getParameter("phoneNumber");

		// Check if any field is empty
		if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || address.isEmpty() || gender.isEmpty()
				|| userName.isEmpty() || phoneNumberStr.isEmpty()) {

			request.setAttribute("error", "Fields cannot be left empty");
			doGet(request, response);
			return;
		}

		Long phoneNumber = Long.parseLong(phoneNumberStr);

		User user = new User();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setAddress(address);
		user.setGender(gender);
		user.setUserName(userName);
		user.setPhoneNumber(phoneNumber);

		try {
			String result = dao.updateUser(user);

			if (result.equals("success")) {
				user = dao.getUserById(id);
				request.setAttribute("user", user);
				request.setAttribute("message", "Successfully Updated Your Data");
				request.getRequestDispatcher(MyConstants.PROFILE_PAGE).forward(request, response);
			} else if (result.equals("UsernameTakenByOther")) {
				request.setAttribute("user", user);
				request.setAttribute("error", "Username already exists");
				request.getRequestDispatcher(MyConstants.PROFILE_PAGE).forward(request, response);
			} else if (result.equals("EmailTakenByOther")) {
				request.setAttribute("error", "Email already exists");
				request.setAttribute("user", user);
				request.getRequestDispatcher(MyConstants.PROFILE_PAGE).forward(request, response);
			} else if (result.equals("PhoneNumberTakenByOther")) {
				request.setAttribute("error", "Phonenumber already exists");
				request.setAttribute("user", user);
				request.getRequestDispatcher(MyConstants.PROFILE_PAGE).forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("error", "An error occurred during Updating.");
			request.getRequestDispatcher(MyConstants.PROFILE_PAGE).forward(request, response);
		}

	}

}
