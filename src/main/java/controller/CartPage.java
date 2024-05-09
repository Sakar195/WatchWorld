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

import appConstant.MyConstants;
import model.cartItem;
import service.OrderDao;

/**
 * Servlet implementation class CartPage
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Cart" })
public class CartPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private OrderDao dao;
       
    @Override
    	public void init(ServletConfig config) throws ServletException {
    		
    		super.init(config);
    		dao = new OrderDao();
    	}
    public CartPage() {
        super();
     
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int userId = (int) request.getSession().getAttribute("userId");
		
		try {
			List<cartItem> cartItems = dao.getCartItems(userId);
			
			request.setAttribute("cartItems", cartItems);
			request.getRequestDispatcher(MyConstants.CART_PAGE).forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving cart items");
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
