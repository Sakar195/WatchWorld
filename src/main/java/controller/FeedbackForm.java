package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FeedbackDao;

/**
 * Servlet implementation class FeedbackForm
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/feedback" })
public class FeedbackForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FeedbackDao feedbackDao;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		feedbackDao = new FeedbackDao();
	}

	public FeedbackForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String phoneNumber = request.getParameter("phoneNumber");
		String message = request.getParameter("feedback");
		try {
			feedbackDao.saveFeedback(email,phoneNumber,message); // Save feedback in the database
			response.sendRedirect(request.getContextPath() + "/About?success=true");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/About?error=true");
		}
	}

}
