package controller;

import java.io.IOException;
import java.lang.System.Logger;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.OrderDao;

/**
 * Servlet implementation class RemoveItemFromCart
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/RemoveItem" })
public class RemoveItemFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
	private OrderDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao = new OrderDao();
	}

	public RemoveItemFromCart() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		Integer userId = (Integer) session.getAttribute("userId");
		try {
			int productId = Integer.parseInt(request.getParameter("product_id"));
			
			
			dao.deleteCartItem(userId,productId);
			session.setAttribute("removeMessage", "Item successfully removed from cart.");
			response.sendRedirect(request.getContextPath() + "/Cart");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in removing item from cart");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error removing item from cart");
		}

	}
}
