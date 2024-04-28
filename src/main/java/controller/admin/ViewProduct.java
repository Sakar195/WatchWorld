package controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appConstant.MyConstants;
import model.product;
import service.ProductDao;

/**
 * Servlet implementation class ViewProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/VProduct" })
public class ViewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao = new ProductDao();
	}

	public ViewProduct() {
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

		try {
			List<product> listOfProduct = dao.getProductDetails();

			for (product productdb : listOfProduct) {
				String base64ImageData = Base64.getEncoder().encodeToString(productdb.getImage_data());
				productdb.setBase64ImageData(base64ImageData);
			}
			request.setAttribute("listOfProduct", listOfProduct);
			request.getRequestDispatcher(MyConstants.VIEWPRODUCT_PAGE).forward(request, response);

		} catch (SQLException e) {
			request.setAttribute("error", "An error occurred while fetching product details.");
			request.getRequestDispatcher("Error").forward(request, response);
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
