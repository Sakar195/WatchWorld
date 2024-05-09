package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import appConstant.MyConstants;
import model.product;
import service.ProductDao;

/**
 * Servlet implementation class ProductDetails
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Details" })
public class ProductDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao dao;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		dao = new ProductDao();
	}

	public ProductDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("product_id"));

		HttpSession session = request.getSession(false); // Get the current session
		System.out.println("works1");
		if (session == null) {
			// No session, redirect to a login or error page
			response.sendRedirect(request.getContextPath() + "/Login");
		}
		
		

		else {
			// ID exists, Fetch a product by ID and forward ProductDetails page
			product Product;
			try {
				System.out.println("userName"+session.getAttribute("username"));
				Product = dao.getProductById(id);
				System.out.println("this is the product id"+id);
				request.setAttribute("Product", Product);
				request.getRequestDispatcher(MyConstants.PRODUCT_DETAILS_PAGE).forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//session.setAttribute("id", id);

			System.out.println("works3");
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
