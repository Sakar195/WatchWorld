package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import appConstant.MyConstants;
import model.product;
import service.ProductDao;

@WebServlet(asyncSupported = true, urlPatterns = { "/Product" })
public class UserProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProduct() {
		super();

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			ProductDao dao = new ProductDao();
			List<product> listOfProduct;
			listOfProduct = dao.getProductDetails();
			
			System.out.println("Retrieved product list with " + listOfProduct.size() + " items.");
			
			
			request.setAttribute("productlist", listOfProduct);
			request.getRequestDispatcher(MyConstants.PRODUCT_PAGE).forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
