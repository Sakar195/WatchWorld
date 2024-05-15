package controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.OrderDao;

/**
 * Servlet implementation class UpdateQuantityOnCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/UpdateCart" })
public class UpdateQuantityOnCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		dao = new OrderDao();
	}

	public UpdateQuantityOnCart() {
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
		request.getParameterMap().forEach((key, value) -> {
			if (key.startsWith("quantity_")&& !key.equals("product_id")) {
				String productIdStr = key.split("_")[1]; // Extract the product ID from the parameter name
				int productId = Integer.parseInt(productIdStr);
				int quantity = Integer.parseInt(value[0]); // Get the quantity

				try {
					dao.updateCartItemQuantity(productId, quantity); // Update the quantity in the database
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		session.setAttribute("updateMessage", "Your cart has been updated");
		// Redirect back to the cart page after updating
		response.sendRedirect(request.getContextPath() + "/Cart");

	}

}
