package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appConstant.MyConstants;
import model.cartItem;
import model.product;
import service.ProductDao;

/**
 * Servlet implementation class FilterProducts
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/FProducts" })
public class FilterProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao productDao;

	@Override
	public void init() throws ServletException {
		productDao = new ProductDao();
	}

	public FilterProducts() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nameFilter = request.getParameter("name");
		String maxPriceStr = request.getParameter("price");

		Integer maxPrice = null;
		if (maxPriceStr != null && !maxPriceStr.trim().isEmpty()) {
			try {
				maxPrice = Integer.parseInt(maxPriceStr);
			} catch (NumberFormatException e) {
				maxPrice = null;
			}
		}

		try {
			List<product> filteredProducts = productDao.getProductsByFilter(nameFilter, maxPrice);
			request.setAttribute("productlist", filteredProducts);
			request.getRequestDispatcher(MyConstants.PRODUCT_PAGE).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving filtered products");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
