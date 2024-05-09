package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.MyConstants;
import service.OrderDao;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddCart" })
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		dao = new OrderDao();
	}

	public AddToCart() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("userId") == null) {
			System.out.println("session is null");
			return;
		}
		Integer userId = (Integer) session.getAttribute("userId");
		System.out.println("this is the user id" + userId);
		int productId = Integer.parseInt(request.getParameter("product_id"));
		System.out.println("this is the product ID" + productId);

		// String status = "order Placed";
		// String status = "Shipped";
		// String status = "Delivered";

		// LocalDate currentDate = LocalDate.now();
		// System.out.println("Current date and time: " + currentDate);
		String referrer = request.getParameter("referrer"); // Get the referrer parameter
		

		try {
			boolean success = false;
			success = dao.addCartItem(userId, productId, 1);
			if (success) {
				if (referrer.equals("productDetails")) {
					response.sendRedirect(request.getContextPath() + "/Details?product_id="+productId+"&added_to_cart=true");
				} else {
					response.sendRedirect(request.getContextPath() + "/Product?added_to_cart=true");
				}
				System.out.println(referrer);
			} else {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to add item to cart.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
