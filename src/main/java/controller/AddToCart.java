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
		String productIdStr = request.getParameter("product_id");
		if (productIdStr == null || productIdStr.isEmpty()) {
			response.sendRedirect("/Product?error=missing_product_id");
			return;
		}
		int productId;
		try {
			productId = Integer.parseInt(productIdStr);
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Product ID.");
			return;
		}

		// String status = "order Placed";
		// String status = "Shipped";
		// String status = "Delivered";

		// LocalDate currentDate = LocalDate.now();
		// System.out.println("Current date and time: " + currentDate);
		String referrer = request.getParameter("referrer"); // Get the referrer parameter

		if (referrer == null || referrer.isEmpty()) {
			referrer = "Product"; // Default referrer if missing
		}
		try {
			boolean success = false;
			success = dao.addCartItem(userId, productId, 1);
			System.out.println("entered add to cart");
			if (success) {
				if (referrer.equals("productDetails")) {
					response.sendRedirect(
							request.getContextPath() + "/Details?product_id=" + productId + "&added_to_cart=true");
					System.out.println("entered add to cart1");

				} else {
					response.sendRedirect(
							request.getContextPath() + "/Product?added_to_cart=true&cart_error=item_already_exists");
					System.out.println("entered add to cart2");

				}
				System.out.println(referrer);
			} else {
				session.setAttribute("cartMessage", "Item already exists in the cart.");
				if (referrer.equals("productDetails")) {
					response.sendRedirect(request.getContextPath() + "/Details?product_id=" + productId
							+ "&added_to_cart=false&cart_error=item_already_exists");

				} else {
					response.sendRedirect(request.getContextPath() + "/Product?added_to_cart=false&cart_error=item_already_exists");

				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Catch Error, Product already exists in the cart");
		}

	}

}
