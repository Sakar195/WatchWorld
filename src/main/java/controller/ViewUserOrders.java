package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.MyConstants;
import model.order;
import service.OrderDao;

/**
 * Servlet implementation class ViewUserOrders
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UserOrders" })
public class ViewUserOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewUserOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Integer userId = (Integer) session.getAttribute("userId");

		OrderDao dao = new OrderDao();
		try {
			List<order> userOrders = dao.getOrdersByUserId(userId);
			request.setAttribute("userOrders", userOrders);
			request.getRequestDispatcher(MyConstants.USER_ORDER_PAGE).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace(); // Handle exception appropriately
			// Optionally, redirect to an error page
			response.sendRedirect(request.getContextPath() + "/Error");
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
