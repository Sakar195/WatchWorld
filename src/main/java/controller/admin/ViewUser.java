package controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appConstant.MyConstants;
import model.User;
import service.UserDao;

/**
 * Servlet implementation class ViewUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/User" })
public class ViewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao = new UserDao();
	}

	public ViewUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			List<User> userList = dao.getAllUser();
			request.setAttribute("userList", userList);
			request.getRequestDispatcher(MyConstants.VIEW_USER_PAGE).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace(); // Handle exception appropriately
			// Redirect to an error page or display an error message
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
