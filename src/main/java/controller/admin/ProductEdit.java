package controller.admin;

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
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int product_id = Integer.valueOf(request.getParameter("id"));
		System.out.println(product_id);

		HttpSession session = request.getSession(false); // Get the current session
		System.out.println("works1");

		// ID exists, Fetch a product by ID and forward to an update page
		System.out.println("works5");
		product Product;
		try {
			Product = dao.getProductById(product_id);
			session.setAttribute("product_id", product_id);

			System.out.println("works6");
			request.setAttribute("Product", Product);
			request.getRequestDispatcher(MyConstants.UPDATE_PAGE).forward(request, response);
			System.out.println("works3");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
