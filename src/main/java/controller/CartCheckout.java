package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.cartItem;
import service.OrderDao;

/**
 * Servlet implementation class CartCheckout
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Checkout" })
public class CartCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		dao = new OrderDao();
	}

	public CartCheckout() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null || session.getAttribute("userId") == null) {
			response.sendRedirect(request.getContextPath() + "/Login");
			return;
		}

		Integer userId = (Integer) session.getAttribute("userId");

		try {
			List<cartItem> cartItems = dao.getCartItems(userId);
			if (cartItems == null || cartItems.isEmpty()) {
				session.setAttribute("checkoutMessage", "Your cart is empty, nothing to checkout.");
				response.sendRedirect(request.getContextPath() + "/Cart");
				return;
			}

			dao.createOrder(userId, cartItems);
			// Set the success message in the session
			session.setAttribute("checkoutMessage", "Your order has been placed successfully.");
			response.sendRedirect(request.getContextPath() + "/Cart");
		} catch (SQLException e) {
			e.printStackTrace();
			session.setAttribute("checkoutMessage", "An error occurred during checkout. Please try again.");
			response.sendRedirect(request.getContextPath() + "/Cart");
		}
	}
}
