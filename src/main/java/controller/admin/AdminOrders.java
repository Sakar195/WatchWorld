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
import model.order;
import service.OrderDao;

/**
 * Servlet implementation class AdminOrders
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Orders" })
public class AdminOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDao dao;
       
   @Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao = new OrderDao();
	}
    public AdminOrders() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            List<order> orders = dao.getAllOrders();
            request.setAttribute("orders", orders); // Set orders as request attribute
            request.getRequestDispatcher(MyConstants.ORDERS_PAGE).forward(request, response); // Forward to JSP
        } catch (SQLException e) {
            throw new ServletException("Error retrieving orders", e);
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
