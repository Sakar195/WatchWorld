package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import appConstant.MyConstants;
import model.product;
import service.ProductDao;

/**
 * Servlet implementation class AddNewProduct
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/AddProduct" })
public class AddNewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(MyConstants.ADDPRODUCT_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String productName = request.getParameter("productName");
		String description = request.getParameter("productDescription");
		int price = 0;
		int quantity = 0;

		try {
		    if (request.getParameter("productPrice") != null) {
		        price = Integer.parseInt(request.getParameter("productPrice"));
		    }
		    if (request.getParameter("Quantity") != null) {
		        quantity = Integer.parseInt(request.getParameter("Quantity"));
		    }
		} catch (NumberFormatException e) {
		    // Handle error: set a default value, return an error message, or log the error
		    price = -1; // Example of a default value
		    quantity = -1; // Example of a default value
		    // Optionally, log the error or notify the user
		}

		Part filePart = request.getPart("productImage");
		String image_Name = filePart.getSubmittedFileName();
		InputStream imageStream = filePart.getInputStream();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		
		int bytesRead;
		int sizeInBytes = 2 * 1024 * 1024; 
		byte[] data = new byte[sizeInBytes];
		
		while ((bytesRead = imageStream.read(data, 0, data.length)) != -1) {
		    buffer.write(data, 0, bytesRead);
		}
		byte[] imageData = buffer.toByteArray();

		product Product = new product();
		
		Product.setImage_data(imageData);
		Product.setImage_name(image_Name);
		Product.setName(productName);
		Product.setDescription(description);
		Product.setPrice(price);
		Product.setQuantity(quantity);
		
		ProductDao dao = new ProductDao();	
		 
		try {
			int row = dao.insertimage(Product);
			if(row>0)
			{
				response.sendRedirect(request.getContextPath()+"/addproduct");
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
