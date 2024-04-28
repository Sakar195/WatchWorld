package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
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
 * Servlet implementation class ProductEdit
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Edit" })
public class ProductEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
		dao = new ProductDao();
	}

	public ProductEdit() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = Integer.valueOf(request.getParameter("id"));
		System.out.println(id);

		try {

			HttpSession session = request.getSession(false); // Get the current session
			System.out.println("works1");
			if (session == null) {
				// No session, redirect to a login or error page
				response.sendRedirect(request.getContextPath() + "/Login");
				System.out.println("works2");
			}

			else {
				// ID exists, Fetch a product by ID and forward to an update page
				System.out.println("works5");
				product Product = dao.getProductById(id);
				System.out.println("works6");
				request.setAttribute("Product", Product);
				request.getRequestDispatcher(MyConstants.UPDATE_PAGE).forward(request, response);
				System.out.println("works3");
			}

		} catch (

		SQLException e) {
			System.out.println("edit servlet failed");
			e.printStackTrace();
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
