package controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderDao;

/**
 * Servlet implementation class DeliverOrder
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Deliver" })
public class DeliverOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private OrderDao orderDao;
    
	@Override
		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			super.init(config);
			orderDao = new OrderDao();
		}
	
	public DeliverOrder() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		 try {
			orderDao.deliverOrder(orderId);
			response.sendRedirect(request.getContextPath() + "/Orders?delivery_success=true");
		} catch (SQLException e) {
			
			e.printStackTrace();
		} // Change status to 'delivered'
		 
	}

}
