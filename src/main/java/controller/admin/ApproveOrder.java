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

import model.cartItem;
import service.OrderDao;
import service.ProductDao;

/**
 * Servlet implementation class ApproveOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Approve" })
public class ApproveOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderDao orderDao;
	private ProductDao productDao;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		orderDao = new OrderDao();
		productDao = new ProductDao();
	}

	public ApproveOrder() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		List<cartItem> orderItems;
		try {
			orderItems = orderDao.getOrderItemsByOrderId(orderId);
			for (cartItem item : orderItems) {
				productDao.decreaseProductQuantity(item.getProductId(), item.getQuantity());
			}
			// Approve the order by updating its status
	        orderDao.approveOrder(orderId);
	        
	        response.sendRedirect(request.getContextPath() + "/Orders?approval_success=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Decrease quantity for each product in product_details
		
	}

}
