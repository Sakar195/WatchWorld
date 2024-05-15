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
import service.UserDao;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/CPassword" })
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private UserDao userDao;
    
	@Override
		public void init(ServletConfig config) throws ServletException {
			// TODO Auto-generated method stub
			super.init(config);
			userDao = new UserDao();
		}
	public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		HttpSession session = request.getSession(false);
		
		int id = (int) session.getAttribute("userId");
		
		if(!newPassword.equals(confirmPassword))
		{
			request.setAttribute("errorMessage", "Password doesnot match");
			request.setAttribute("oldPassword", oldPassword);
			request.setAttribute("errorSource", "change-password"); // Indicate the source of the error
			request.getRequestDispatcher(MyConstants.PROFILE_PAGE).forward(request, response);
		}
		else
		{
			try {
				boolean isChanged = userDao.changePassword(id,oldPassword,newPassword);
				if (isChanged == true) {
					request.setAttribute("errorSource", "change-password");
					request.setAttribute("message1", "Your Password has been changed");
					request.getRequestDispatcher(MyConstants.PROFILE_PAGE).forward(request, response);
				} else {
					request.setAttribute("errorSource", "change-password");
					request.setAttribute("errorMessage", "Incorrect Old Password");
					request.getRequestDispatcher(MyConstants.PROFILE_PAGE).forward(request, response);
				} 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
