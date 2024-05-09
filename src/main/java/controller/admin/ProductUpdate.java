package controller.admin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import appConstant.MyConstants;
import model.product;
import service.ProductDao;

@WebServlet(asyncSupported = true, urlPatterns = { "/Update" })
@MultipartConfig( // Add this annotation to enable multipart support
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class ProductUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		dao = new ProductDao();
	}

	public ProductUpdate() {
		super();
		dao = new ProductDao();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession(false);
		Integer product_id = null;
		if (session == null || session.getAttribute("product_id") == null) {
		    // Session is invalid or the attribute doesn't exist, handle appropriately
		    response.sendRedirect(request.getContextPath() + "/Login"); // Redirect to login or error page
		    System.out.println("getting seeion");
		    return;
		} else {
		    
		    product_id = (int) session.getAttribute("product_id"); // Only cast if not null
		    System.out.println("getting id");
		    
		}

		Part filePart = request.getPart("productImage");
		System.out.println("getting id");
		

		int bytesRead;
		int sizeInBytes = 2 * 1024 * 1024;
		byte[] data = new byte[sizeInBytes];

		byte[] imageData = null;
		String imageName = null;
		if (filePart != null && filePart.getSize() > 0) {
			imageName = filePart.getSubmittedFileName();
			InputStream imageStream = filePart.getInputStream();
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			while ((bytesRead = imageStream.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, bytesRead);
			}
			imageData = buffer.toByteArray();
		} else {
			product existingProduct;
			//if user doesnt upload image, retrieving values form object to set into database
			try {
				existingProduct = dao.getProductById(product_id);
				if (existingProduct != null) {
					imageData = existingProduct.getImage_data();
					imageName = existingProduct.getImage_name();
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		product Product = new product();

		Product.setId(product_id);
		Product.setImage_data(imageData);
		Product.setImage_name(imageName);
		Product.setName(request.getParameter("productName"));
		Product.setDescription(request.getParameter("productDescription"));
		Product.setPrice(Integer.parseInt(request.getParameter("productPrice")));
		Product.setQuantity(Integer.parseInt(request.getParameter("Quantity")));

		try {
			int row = dao.updateProduct(Product);
			if(row>0)
			{
				request.setAttribute("message", "Product Successfully Updated");
				request.getRequestDispatcher(MyConstants.UPDATE_PAGE).forward(request, response);
				//response.sendRedirect(request.getContextPath()+"/VProduct");
			}
			else
			{
				request.setAttribute("error", "problem in updating product");
				request.getRequestDispatcher(MyConstants.UPDATE_PAGE).forward(request, response);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
