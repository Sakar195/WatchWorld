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

import service.ProductDao;

/**
 * Servlet implementation class ProductDelete
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/Delete" })
public class ProductDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDao dao;

	@Override
	public void init(ServletConfig config) throws ServletException {
	
		super.init(config);
		dao = new ProductDao();
	}
    
    public ProductDelete() {
        super();

    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		int id = Integer.valueOf(request.getParameter("id"));
		boolean isDeleted = false;
		try {
			isDeleted = dao.deleteProduct(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(isDeleted)
		{
			HttpSession session = request.getSession(false);
			session.setAttribute("message", "Product Deleted Successfully");
			response.sendRedirect(request.getContextPath()+"/VProduct");
			
		}
		else {
			System.out.println("error in session");
			
		}
	}

}
