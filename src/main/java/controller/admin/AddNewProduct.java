package controller.admin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5, // 5MB
maxFileSize = 1024 * 1024 * 10,      // 10MB
maxRequestSize = 1024 * 1024 * 50)  // 50MB
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher(MyConstants.ADDPRODUCT_PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			request.setAttribute("error", "Invalid number format.");
		    request.getRequestDispatcher(MyConstants.ADDPRODUCT_PAGE).forward(request, response);
		    return; // Exit early if parsing fails

		}
		// retrieves image uploaded
		System.out.println("works1");
		Part filePart = request.getPart("productImage");
		System.out.println(filePart);
		if (filePart == null) {
			request.setAttribute("error", "No file was uploaded."); // Add error message
			request.getRequestDispatcher(MyConstants.ADDPRODUCT_PAGE).forward(request, response);
			return; // Exit early if no file was uploaded
		}
		System.out.println("works2");
		String image_Name = filePart.getSubmittedFileName();
		System.out.println(image_Name);
		// to convert image into bytes
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
		System.out.println("works3");
		// setting product details in products object
		Product.setImage_data(imageData);
		Product.setImage_name(image_Name);
		Product.setName(productName);
		Product.setDescription(description);
		Product.setPrice(price);
		Product.setQuantity(quantity);

		ProductDao dao = new ProductDao();

		try {
			// inserting the image to database
			System.out.println("works4");
			int row = dao.insertimage(Product);
			System.out.println(row);
			if (row > 0) {
				// redirecting to the same page
				request.setAttribute("message", "Product Successfully Added");
				request.getRequestDispatcher(MyConstants.ADDPRODUCT_PAGE).forward(request, response);
			} else {
				System.out.println("works4");
				request.setAttribute("error", "problem in Adding product");
				request.getRequestDispatcher(MyConstants.ADDPRODUCT_PAGE).forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
